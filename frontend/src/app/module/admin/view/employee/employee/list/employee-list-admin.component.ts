import {Component, OnInit} from '@angular/core';
import {EmployeeAdminService} from 'src/app/shared/service/admin/employee/EmployeeAdmin.service';
import {EmployeeDto} from 'src/app/shared/model/employee/Employee.model';
import {EmployeeCriteria} from 'src/app/shared/criteria/employee/EmployeeCriteria.model';


import {ConfirmationService, MessageService,MenuItem} from 'primeng/api';
import {FileTempDto} from 'src/app/zynerator/dto/FileTempDto.model';
import {DatePipe} from '@angular/common';
import {Router} from '@angular/router';
import {Inject, Injectable, PLATFORM_ID} from '@angular/core';

import {environment} from 'src/environments/environment';

import {RoleService} from 'src/app/zynerator/security/shared/service/Role.service';
import {AbstractService} from 'src/app/zynerator/service/AbstractService';
import {BaseDto} from 'src/app/zynerator/dto/BaseDto.model';
import {BaseCriteria} from 'src/app/zynerator/criteria/BaseCriteria.model';
import {StringUtilService} from 'src/app/zynerator/util/StringUtil.service';
import {ServiceLocator} from 'src/app/zynerator/service/ServiceLocator';

import {AuthService} from 'src/app/zynerator/security/shared/service/Auth.service';
import {ExportService} from 'src/app/zynerator/util/Export.service';


import {TypeDiplomeDto} from 'src/app/shared/model/diplome/TypeDiplome.model';
import {TypeDiplomeAdminService} from 'src/app/shared/service/admin/diplome/TypeDiplomeAdmin.service';
import {SituationFamilialeDto} from 'src/app/shared/model/config/SituationFamiliale.model';
import {SituationFamilialeAdminService} from 'src/app/shared/service/admin/config/SituationFamilialeAdmin.service';
import {DiplomeDto} from 'src/app/shared/model/diplome/Diplome.model';
import {DiplomeAdminService} from 'src/app/shared/service/admin/diplome/DiplomeAdmin.service';
import {FonctionDto} from 'src/app/shared/model/config/Fonction.model';
import {FonctionAdminService} from 'src/app/shared/service/admin/config/FonctionAdmin.service';
import {LocalDto} from 'src/app/shared/model/config/Local.model';
import {LocalAdminService} from 'src/app/shared/service/admin/config/LocalAdmin.service';
import {GenreDto} from 'src/app/shared/model/config/Genre.model';
import {GenreAdminService} from 'src/app/shared/service/admin/config/GenreAdmin.service';
import {GradeDto} from 'src/app/shared/model/config/Grade.model';
import {GradeAdminService} from 'src/app/shared/service/admin/config/GradeAdmin.service';
import {UniteStructurelleDto} from 'src/app/shared/model/config/UniteStructurelle.model';
import {UniteStructurelleAdminService} from 'src/app/shared/service/admin/config/UniteStructurelleAdmin.service';


@Component({
  selector: 'app-employee-list-admin',
  templateUrl: './employee-list-admin.component.html'
})
export class EmployeeListAdminComponent implements OnInit {

    protected fileName = 'Employee';

    protected findByCriteriaShow = false;
    protected cols: any[] = [];
    protected excelPdfButons: MenuItem[];
    protected exportData: any[] = [];
    protected criteriaData: any[] = [];
    protected _totalRecords = 0;
    private _pdfName: string;


    protected datePipe: DatePipe;
    protected messageService: MessageService;
    protected confirmationService: ConfirmationService;
    protected roleService: RoleService;
    protected router: Router;
    protected stringUtilService: StringUtilService;
    protected authService: AuthService;
    protected exportService: ExportService;
    protected excelFile: File | undefined;
    protected enableSecurity = false;


    locals: Array<LocalDto>;
    situationFamiliales: Array<SituationFamilialeDto>;
    genres: Array<GenreDto>;
    grades: Array<GradeDto>;
    fonctions: Array<FonctionDto>;
    uniteStructurelles: Array<UniteStructurelleDto>;


    constructor( private service: EmployeeAdminService  , private typeDiplomeService: TypeDiplomeAdminService, private situationFamilialeService: SituationFamilialeAdminService, private diplomeService: DiplomeAdminService, private fonctionService: FonctionAdminService, private localService: LocalAdminService, private genreService: GenreAdminService, private gradeService: GradeAdminService, private uniteStructurelleService: UniteStructurelleAdminService, @Inject(PLATFORM_ID) private platformId?) {
        this.datePipe = ServiceLocator.injector.get(DatePipe);
        this.messageService = ServiceLocator.injector.get(MessageService);
        this.confirmationService = ServiceLocator.injector.get(ConfirmationService);
        this.roleService = ServiceLocator.injector.get(RoleService);
        this.router = ServiceLocator.injector.get(Router);
        this.authService = ServiceLocator.injector.get(AuthService);
        this.exportService = ServiceLocator.injector.get(ExportService);
    }

    ngOnInit(): void {
        this.findPaginatedByCriteria();
        this.initExport();
        this.initCol();
        this.loadLocal();
        this.loadSituationFamiliale();
        this.loadGenre();
        this.loadGrade();
        this.loadFonction();
        this.loadUniteStructurelle();

    }




    public onExcelFileSelected(event: any): void {
        const input = event.target as HTMLInputElement;
        if (input.files && input.files.length > 0) {
            this.excelFile = input.files[0];
        }
    }

    public importExcel(): void {
        if (this.excelFile) {
            this.service.importExcel(this.excelFile).subscribe(
                response => {
                    this.items = response;
                    this.messageService.add({
                        severity: 'success',
                        summary: 'Success',
                        detail: 'File uploaded successfully!',
                        life: 3000
                    });
                },
                error => {
                    this.messageService.add({
                        severity: 'error',
                        summary: 'Error',
                        detail: 'File uploaded with Error!',
                        life: 3000
                    });
                }
            );
        }
    }

    public findPaginatedByCriteria() {
        this.service.findPaginatedByCriteria(this.criteria).subscribe(paginatedItems => {
            this.items = paginatedItems.list;
            this.totalRecords = paginatedItems.dataSize;
            this.selections = new Array<EmployeeDto>();
        }, error => console.log(error));
    }

    public onPage(event: any) {
        this.criteria.page = event.page;
        this.criteria.maxResults = event.rows;
        this.findPaginatedByCriteria();
    }

    public async edit(dto: EmployeeDto) {
        this.service.findByIdWithAssociatedList(dto).subscribe(res => {
            this.item = res;
            console.log(res);
            this.editDialog = true;
        });

    }

    public async view(dto: EmployeeDto) {
        this.service.findByIdWithAssociatedList(dto).subscribe(res => {
            this.item = res;
            this.viewDialog = true;
        });
    }

    public async openCreate() {
        this.item = new EmployeeDto();
        this.createDialog = true;
    }

    public async deleteMultiple() {
        this.confirmationService.confirm({
            message: 'Voulez-vous supprimer ces éléments ?',
            header: 'Confirmation',
            icon: 'pi pi-exclamation-triangle',
            accept: () => {
                this.service.deleteMultiple().subscribe(() => {
                    for (let selection of this.selections) {
                        let index = this.items.findIndex(element => element.id === selection.id);
                        this.items.splice(index,1);
                    }
                    this.selections = new Array<EmployeeDto>();
                    this.messageService.add({
                        severity: 'success',
                        summary: 'Succès',
                        detail: 'Les éléments sélectionnés ont été supprimés',
                        life: 3000
                    });

                }, error => console.log(error));
            }
        });
    }


    public isSelectionDisabled(): boolean {
        return this.selections == null || this.selections.length == 0;
    }


    public async delete(dto: EmployeeDto) {

        this.confirmationService.confirm({
            message: 'Voulez-vous supprimer cet élément ?',
            header: 'Confirmation',
            icon: 'pi pi-exclamation-triangle',
            accept: () => {
                this.service.delete(dto).subscribe(status => {
                    if (status > 0) {
                        const position = this.items.indexOf(dto);
                        position > -1 ? this.items.splice(position, 1) : false;
                        this.messageService.add({
                            severity: 'success',
                            summary: 'Succès',
                            detail: 'Element Supprimé',
                            life: 3000
                        });
                    }

                }, error => console.log(error));
            }
        });

    }

    public async duplicate(dto: EmployeeDto) {
        this.service.findByIdWithAssociatedList(dto).subscribe(
            res => {
                this.initDuplicate(res);
                this.item = res;
                this.item.id = null;
                this.createDialog = true;
            });
    }

    // TODO : check if correct
    public initExport(): void {
        this.excelPdfButons = [
            {
                label: 'CSV', icon: 'pi pi-file', command: () => {
                    this.prepareColumnExport();
                    this.exportService.exporterCSV(this.criteriaData, this.exportData, this.fileName);
                }
            },
            {
                label: 'XLS', icon: 'pi pi-file-excel', command: () => {
                    this.prepareColumnExport();
                    this.exportService.exporterExcel(this.criteriaData, this.exportData, this.fileName);
                }
            },
            {
                label: 'PDF', icon: 'pi pi-file-pdf', command: () => {
                    this.prepareColumnExport();
                    this.exportService.exporterPdf(this.criteriaData, this.exportData, this.fileName);
                }
            }
        ];
    }

    public exportPdf(dto: EmployeeDto): void {
        this.service.exportPdf(dto).subscribe((data: ArrayBuffer) => {
            const blob = new Blob([data], {type: 'application/pdf'});
            const url = window.URL.createObjectURL(blob);
            const link = document.createElement('a');
            link.href = url;
            link.download = this.pdfName;
            link.setAttribute('target', '_blank'); // open link in new tab
            link.click();
            window.URL.revokeObjectURL(url);
        }, (error) => {
            console.error(error); // handle any errors that occur
        });
    }

    public showSearch(): void {
        this.findByCriteriaShow = !this.findByCriteriaShow;
    }


    update() {
        this.service.edit().subscribe(data => {
            const myIndex = this.items.findIndex(e => e.id === this.item.id);
            this.items[myIndex] = data;
            this.editDialog = false;
            this.item = new EmployeeDto();
        } , error => {
            console.log(error);
        });
    }

    public save() {
        this.service.save().subscribe(item => {
            if (item != null) {
                this.items.push({...item});
                this.createDialog = false;


                this.item = new EmployeeDto();
            } else {
                this.messageService.add({severity: 'error', summary: 'Erreurs', detail: 'Element existant'});
            }
        }, error => {
            console.log(error);
        });
    }

// add


    public initCol() {
        this.cols = [
            {field: 'ppr', header: 'Ppr'},
            {field: 'nom', header: 'Nom'},
            {field: 'prenom', header: 'Prenom'},
            {field: 'lieuNaissance', header: 'Lieu naissance'},
            {field: 'photoUrl', header: 'Photo url'},
            {field: 'telephone', header: 'Telephone'},
            {field: 'address', header: 'Address'},
            {field: 'email', header: 'Email'},
            {field: 'local?.libelle', header: 'Local'},
            {field: 'situationFamiliale?.libelle', header: 'Situation familiale'},
            {field: 'genre?.libelle', header: 'Genre'},
            {field: 'grade?.libelle', header: 'Grade'},
            {field: 'fonction?.libelle', header: 'Fonction'},
            {field: 'uniteStructurelle?.code', header: 'Unite structurelle'},
        ];
    }


    public async loadLocal(){
        this.localService.findAllOptimized().subscribe(locals => this.locals = locals, error => console.log(error))
    }
    public async loadSituationFamiliale(){
        this.situationFamilialeService.findAllOptimized().subscribe(situationFamiliales => this.situationFamiliales = situationFamiliales, error => console.log(error))
    }
    public async loadGenre(){
        this.genreService.findAllOptimized().subscribe(genres => this.genres = genres, error => console.log(error))
    }
    public async loadGrade(){
        this.gradeService.findAllOptimized().subscribe(grades => this.grades = grades, error => console.log(error))
    }
    public async loadFonction(){
        this.fonctionService.findAllOptimized().subscribe(fonctions => this.fonctions = fonctions, error => console.log(error))
    }
    public async loadUniteStructurelle(){
        this.uniteStructurelleService.findAllOptimized().subscribe(uniteStructurelles => this.uniteStructurelles = uniteStructurelles, error => console.log(error))
    }


	public initDuplicate(res: EmployeeDto) {
        if (res.diplome != null) {
             res.diplome.forEach(d => { d.employee = null; d.id = null; });
        }
	}


    public prepareColumnExport(): void {
        this.service.findByCriteria(this.criteria).subscribe(
            (allItems) =>{
                this.exportData = allItems.map(e => {
					return {
						'Ppr': e.ppr ,
						'Nom': e.nom ,
						'Prenom': e.prenom ,
						'Lieu naissance': e.lieuNaissance ,
						'Photo url': e.photoUrl ,
						'Telephone': e.telephone ,
						'Address': e.address ,
						'Email': e.email ,
						'Local': e.local?.libelle ,
						'Situation familiale': e.situationFamiliale?.libelle ,
						'Genre': e.genre?.libelle ,
						'Grade': e.grade?.libelle ,
						'Fonction': e.fonction?.libelle ,
						'Unite structurelle': e.uniteStructurelle?.code ,
					}
				});

            this.criteriaData = [{
                'Ppr': this.criteria.ppr ? this.criteria.ppr : environment.emptyForExport ,
                'Nom': this.criteria.nom ? this.criteria.nom : environment.emptyForExport ,
                'Prenom': this.criteria.prenom ? this.criteria.prenom : environment.emptyForExport ,
                'Lieu naissance': this.criteria.lieuNaissance ? this.criteria.lieuNaissance : environment.emptyForExport ,
                'Photo url': this.criteria.photoUrl ? this.criteria.photoUrl : environment.emptyForExport ,
                'Telephone': this.criteria.telephone ? this.criteria.telephone : environment.emptyForExport ,
                'Address': this.criteria.address ? this.criteria.address : environment.emptyForExport ,
                'Email': this.criteria.email ? this.criteria.email : environment.emptyForExport ,
            //'Local': this.criteria.local?.libelle ? this.criteria.local?.libelle : environment.emptyForExport ,
            //'Situation familiale': this.criteria.situationFamiliale?.libelle ? this.criteria.situationFamiliale?.libelle : environment.emptyForExport ,
            //'Genre': this.criteria.genre?.libelle ? this.criteria.genre?.libelle : environment.emptyForExport ,
            //'Grade': this.criteria.grade?.libelle ? this.criteria.grade?.libelle : environment.emptyForExport ,
            //'Fonction': this.criteria.fonction?.libelle ? this.criteria.fonction?.libelle : environment.emptyForExport ,
            //'Unite structurelle': this.criteria.uniteStructurelle?.code ? this.criteria.uniteStructurelle?.code : environment.emptyForExport ,
            }];
			}

        )
    }


    get items(): Array<EmployeeDto> {
        return this.service.items;
    }

    set items(value: Array<EmployeeDto>) {
        this.service.items = value;
    }

    get selections(): Array<EmployeeDto> {
        return this.service.selections;
    }

    set selections(value: Array<EmployeeDto>) {
        this.service.selections = value;
    }

    get item(): EmployeeDto {
        return this.service.item;
    }

    set item(value: EmployeeDto) {
        this.service.item = value;
    }

    get createDialog(): boolean {
        return this.service.createDialog;
    }

    set createDialog(value: boolean) {
        this.service.createDialog = value;
    }

    get editDialog(): boolean {
        return this.service.editDialog;
    }

    set editDialog(value: boolean) {
        this.service.editDialog = value;
    }

    get viewDialog(): boolean {
        return this.service.viewDialog;
    }

    set viewDialog(value: boolean) {
        this.service.viewDialog = value;
    }

    get criteria(): EmployeeCriteria {
        return this.service.criteria;
    }

    set criteria(value: EmployeeCriteria) {
        this.service.criteria = value;
    }

    get dateFormat() {
        return environment.dateFormatList;
    }


    get totalRecords(): number {
        return this._totalRecords;
    }

    set totalRecords(value: number) {
        this._totalRecords = value;
    }

    get pdfName(): string {
        return this._pdfName;
    }

    set pdfName(value: string) {
        this._pdfName = value;
    }

    get createActionIsValid(): boolean {
        return this.service.createActionIsValid;
    }

    set createActionIsValid(value: boolean) {
        this.service.createActionIsValid = value;
    }


    get editActionIsValid(): boolean {
        return this.service.editActionIsValid;
    }

    set editActionIsValid(value: boolean) {
        this.service.editActionIsValid = value;
    }

    get listActionIsValid(): boolean {
        return this.service.listActionIsValid;
    }

    set listActionIsValid(value: boolean) {
        this.service.listActionIsValid = value;
    }

    get deleteActionIsValid(): boolean {
        return this.service.deleteActionIsValid;
    }

    set deleteActionIsValid(value: boolean) {
        this.service.deleteActionIsValid = value;
    }


    get viewActionIsValid(): boolean {
        return this.service.viewActionIsValid;
    }

    set viewActionIsValid(value: boolean) {
        this.service.viewActionIsValid = value;
    }

    get duplicateActionIsValid(): boolean {
        return this.service.duplicateActionIsValid;
    }

    set duplicateActionIsValid(value: boolean) {
        this.service.duplicateActionIsValid = value;
    }

    get createAction(): string {
        return this.service.createAction;
    }

    set createAction(value: string) {
        this.service.createAction = value;
    }

    get listAction(): string {
        return this.service.listAction;
    }

    set listAction(value: string) {
        this.service.listAction = value;
    }

    get editAction(): string {
        return this.service.editAction;
    }

    set editAction(value: string) {
        this.service.editAction = value;
    }

    get deleteAction(): string {
        return this.service.deleteAction;
    }

    set deleteAction(value: string) {
        this.service.deleteAction = value;
    }

    get viewAction(): string {
        return this.service.viewAction;
    }

    set viewAction(value: string) {
        this.service.viewAction = value;
    }

    get duplicateAction(): string {
        return this.service.duplicateAction;
    }

    set duplicateAction(value: string) {
        this.service.duplicateAction = value;
    }

    get entityName(): string {
        return this.service.entityName;
    }

    set entityName(value: string) {
        this.service.entityName = value;
    }
}
