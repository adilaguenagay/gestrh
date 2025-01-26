import {Component, OnInit, Input} from '@angular/core';
import {ConfirmationService, MessageService} from 'primeng/api';
import {FileTempDto} from 'src/app/zynerator/dto/FileTempDto.model';
import {DatePipe} from '@angular/common';
import {Router} from '@angular/router';
import {Inject, Injectable, PLATFORM_ID} from '@angular/core';

import {environment} from 'src/environments/environment';

import {RoleService} from 'src/app/zynerator/security/shared/service/Role.service';
import {StringUtilService} from 'src/app/zynerator/util/StringUtil.service';
import {ServiceLocator} from 'src/app/zynerator/service/ServiceLocator';




import {UniteStructurelleAdminService} from 'src/app/shared/service/admin/config/UniteStructurelleAdmin.service';
import {UniteStructurelleDto} from 'src/app/shared/model/config/UniteStructurelle.model';
import {UniteStructurelleCriteria} from 'src/app/shared/criteria/config/UniteStructurelleCriteria.model';


import {EmployeeDto} from 'src/app/shared/model/employee/Employee.model';
import {EmployeeAdminService} from 'src/app/shared/service/admin/employee/EmployeeAdmin.service';
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

@Component({
  selector: 'app-unite-structurelle-edit-admin',
  templateUrl: './unite-structurelle-edit-admin.component.html'
})
export class UniteStructurelleEditAdminComponent implements OnInit {

	protected _submitted = false;
    protected _errorMessages = new Array<string>();


    protected datePipe: DatePipe;
    protected messageService: MessageService;
    protected confirmationService: ConfirmationService;
    protected roleService: RoleService;
    protected router: Router;
    protected stringUtilService: StringUtilService;
    private _activeTab = 0;
    private _file: any;
    private _files: any;

    protected employeeIndex = -1;

    private _employeeElement = new EmployeeDto();

    private _validUniteStructurelleCode = true;

    private _validUniteMereCode = true;
    private _validEmployeePpr = true;



    constructor(private service: UniteStructurelleAdminService , private employeeService: EmployeeAdminService, private situationFamilialeService: SituationFamilialeAdminService, private fonctionService: FonctionAdminService, private localService: LocalAdminService, private genreService: GenreAdminService, private gradeService: GradeAdminService, @Inject(PLATFORM_ID) private platformId?) {
        this.datePipe = ServiceLocator.injector.get(DatePipe);
        this.messageService = ServiceLocator.injector.get(MessageService);
        this.confirmationService = ServiceLocator.injector.get(ConfirmationService);
        this.roleService = ServiceLocator.injector.get(RoleService);
        this.router = ServiceLocator.injector.get(Router);
        this.stringUtilService = ServiceLocator.injector.get(StringUtilService);
    }

    ngOnInit(): void {
        this.employeeElement.local = new LocalDto();
        this.localService.findAll().subscribe((data) => this.locals = data);
        this.employeeElement.situationFamiliale = new SituationFamilialeDto();
        this.situationFamilialeService.findAll().subscribe((data) => this.situationFamiliales = data);
        this.employeeElement.genre = new GenreDto();
        this.genreService.findAll().subscribe((data) => this.genres = data);
        this.employeeElement.grade = new GradeDto();
        this.gradeService.findAll().subscribe((data) => this.grades = data);
        this.employeeElement.fonction = new FonctionDto();
        this.fonctionService.findAll().subscribe((data) => this.fonctions = data);

        this.service.findAll().subscribe((data) => this.uniteMeres = data);
    }

    public prepareEdit() {
    }



 public edit(): void {
        this.submitted = true;
        this.prepareEdit();
        this.validateForm();
        if (this.errorMessages.length === 0) {
            this.editWithShowOption(false);
        } else {
            this.messageService.add({
                severity: 'error',
                summary: 'Erreurs',
                detail: 'Merci de corrigé les erreurs sur le formulaire'
            });
        }
    }

    public editWithShowOption(showList: boolean) {
        this.service.edit().subscribe(religion=>{
            const myIndex = this.items.findIndex(e => e.id === this.item.id);
            this.items[myIndex] = religion;
            this.editDialog = false;
            this.submitted = false;
            this.item = new UniteStructurelleDto();
        } , error =>{
            console.log(error);
        });
    }

    public hideEditDialog() {
        this.editDialog = false;
        this.setValidation(true);
    }





    public validateEmployee(){
        this.errorMessages = new Array();
        this.validateEmployeePpr();
    }

    public setValidation(value: boolean){
        this.validUniteStructurelleCode = value;
        this.validEmployeePpr = value;
    }

    public addEmployee() {
        if( this.item.employee == null )
            this.item.employee = new Array<EmployeeDto>();

       this.validateEmployee();

       if (this.errorMessages.length === 0) {
            if (this.employeeIndex == -1){
                this.item.employee.push({... this.employeeElement});
            }else {
                this.item.employee[this.employeeIndex] =this.employeeElement;
            }
              this.employeeElement = new EmployeeDto();
              this.employeeIndex = -1;
       }else{
           this.messageService.add({severity: 'error',summary: 'Erreurs',detail: 'Merci de corrigé les erreurs suivant : ' + this.errorMessages});
       }
    }

    public deleteEmployee(p: EmployeeDto, index: number) {
        this.item.employee.splice(index, 1);
    }

    public editEmployee(p: EmployeeDto, index: number) {
        this.employeeElement = {... p};
        this.employeeIndex = index;
        this.activeTab = 0;
    }

    public validateForm(): void{
        this.errorMessages = new Array<string>();
        this.validateUniteStructurelleCode();
    }

    public validateUniteStructurelleCode(){
        if (this.stringUtilService.isEmpty(this.item.code)) {
            this.errorMessages.push('Code non valide');
            this.validUniteStructurelleCode = false;
        } else {
            this.validUniteStructurelleCode = true;
        }
    }



    private validateEmployeePpr(){
        if (this.employeeElement.ppr == null) {
        this.errorMessages.push('Ppr de la employee est  invalide');
            this.validEmployeePpr = false;
        } else {
            this.validEmployeePpr = true;
        }
    }

   public async openCreateSituationFamiliale(situationFamiliale: string) {
        const isPermistted = await this.roleService.isPermitted('SituationFamiliale', 'edit');
        if (isPermistted) {
             this.situationFamiliale = new SituationFamilialeDto();
             this.createSituationFamilialeDialog = true;
        }else {
             this.messageService.add({
                severity: 'error', summary: 'erreur', detail: 'problème de permission'
            });
        }
    }
   public async openCreateGrade(grade: string) {
        const isPermistted = await this.roleService.isPermitted('Grade', 'edit');
        if (isPermistted) {
             this.grade = new GradeDto();
             this.createGradeDialog = true;
        }else {
             this.messageService.add({
                severity: 'error', summary: 'erreur', detail: 'problème de permission'
            });
        }
    }
   public async openCreateLocal(local: string) {
        const isPermistted = await this.roleService.isPermitted('Local', 'edit');
        if (isPermistted) {
             this.local = new LocalDto();
             this.createLocalDialog = true;
        }else {
             this.messageService.add({
                severity: 'error', summary: 'erreur', detail: 'problème de permission'
            });
        }
    }
   public async openCreateFonction(fonction: string) {
        const isPermistted = await this.roleService.isPermitted('Fonction', 'edit');
        if (isPermistted) {
             this.fonction = new FonctionDto();
             this.createFonctionDialog = true;
        }else {
             this.messageService.add({
                severity: 'error', summary: 'erreur', detail: 'problème de permission'
            });
        }
    }
   public async openCreateUniteMere(uniteMere: string) {
        const isPermistted = await this.roleService.isPermitted('UniteStructurelle', 'edit');
        if (isPermistted) {
             this.uniteMere = new UniteStructurelleDto();
             this.createUniteMereDialog = true;
        }else {
             this.messageService.add({
                severity: 'error', summary: 'erreur', detail: 'problème de permission'
            });
        }
    }
   public async openCreateGenre(genre: string) {
        const isPermistted = await this.roleService.isPermitted('Genre', 'edit');
        if (isPermistted) {
             this.genre = new GenreDto();
             this.createGenreDialog = true;
        }else {
             this.messageService.add({
                severity: 'error', summary: 'erreur', detail: 'problème de permission'
            });
        }
    }

    get situationFamiliale(): SituationFamilialeDto {
        return this.situationFamilialeService.item;
    }
    set situationFamiliale(value: SituationFamilialeDto) {
        this.situationFamilialeService.item = value;
    }
    get situationFamiliales(): Array<SituationFamilialeDto> {
        return this.situationFamilialeService.items;
    }
    set situationFamiliales(value: Array<SituationFamilialeDto>) {
        this.situationFamilialeService.items = value;
    }
    get createSituationFamilialeDialog(): boolean {
        return this.situationFamilialeService.createDialog;
    }
    set createSituationFamilialeDialog(value: boolean) {
        this.situationFamilialeService.createDialog= value;
    }
    get grade(): GradeDto {
        return this.gradeService.item;
    }
    set grade(value: GradeDto) {
        this.gradeService.item = value;
    }
    get grades(): Array<GradeDto> {
        return this.gradeService.items;
    }
    set grades(value: Array<GradeDto>) {
        this.gradeService.items = value;
    }
    get createGradeDialog(): boolean {
        return this.gradeService.createDialog;
    }
    set createGradeDialog(value: boolean) {
        this.gradeService.createDialog= value;
    }
    get local(): LocalDto {
        return this.localService.item;
    }
    set local(value: LocalDto) {
        this.localService.item = value;
    }
    get locals(): Array<LocalDto> {
        return this.localService.items;
    }
    set locals(value: Array<LocalDto>) {
        this.localService.items = value;
    }
    get createLocalDialog(): boolean {
        return this.localService.createDialog;
    }
    set createLocalDialog(value: boolean) {
        this.localService.createDialog= value;
    }
    get fonction(): FonctionDto {
        return this.fonctionService.item;
    }
    set fonction(value: FonctionDto) {
        this.fonctionService.item = value;
    }
    get fonctions(): Array<FonctionDto> {
        return this.fonctionService.items;
    }
    set fonctions(value: Array<FonctionDto>) {
        this.fonctionService.items = value;
    }
    get createFonctionDialog(): boolean {
        return this.fonctionService.createDialog;
    }
    set createFonctionDialog(value: boolean) {
        this.fonctionService.createDialog= value;
    }
    get uniteMere(): UniteStructurelleDto {
        return this.service.item;
    }
    set uniteMere(value: UniteStructurelleDto) {
        this.service.item = value;
    }
    get uniteMeres(): Array<UniteStructurelleDto> {
        return this.service.items;
    }
    set uniteMeres(value: Array<UniteStructurelleDto>) {
        this.service.items = value;
    }
    get createUniteMereDialog(): boolean {
        return this.service.createDialog;
    }
    set createUniteMereDialog(value: boolean) {
        this.service.createDialog= value;
    }
    get genre(): GenreDto {
        return this.genreService.item;
    }
    set genre(value: GenreDto) {
        this.genreService.item = value;
    }
    get genres(): Array<GenreDto> {
        return this.genreService.items;
    }
    set genres(value: Array<GenreDto>) {
        this.genreService.items = value;
    }
    get createGenreDialog(): boolean {
        return this.genreService.createDialog;
    }
    set createGenreDialog(value: boolean) {
        this.genreService.createDialog= value;
    }

    get employeeElement(): EmployeeDto {
        if( this._employeeElement == null )
            this._employeeElement = new EmployeeDto();
         return this._employeeElement;
    }

    set employeeElement(value: EmployeeDto) {
        this._employeeElement = value;
    }

    get validUniteStructurelleCode(): boolean {
        return this._validUniteStructurelleCode;
    }
    set validUniteStructurelleCode(value: boolean) {
        this._validUniteStructurelleCode = value;
    }

    get validUniteMereCode(): boolean {
        return this._validUniteMereCode;
    }
    set validUniteMereCode(value: boolean) {
        this._validUniteMereCode = value;
    }
    get validEmployeePpr(): boolean {
        return this._validEmployeePpr;
    }
    set validEmployeePpr(value: boolean) {
        this._validEmployeePpr = value;
    }

	get items(): Array<UniteStructurelleDto> {
        return this.service.items;
    }

    set items(value: Array<UniteStructurelleDto>) {
        this.service.items = value;
    }

    get item(): UniteStructurelleDto {
        return this.service.item;
    }

    set item(value: UniteStructurelleDto) {
        this.service.item = value;
    }

    get editDialog(): boolean {
        return this.service.editDialog;
    }

    set editDialog(value: boolean) {
        this.service.editDialog = value;
    }

    get criteria(): UniteStructurelleCriteria {
        return this.service.criteria;
    }

    set criteria(value: UniteStructurelleCriteria) {
        this.service.criteria = value;
    }

    get dateFormat() {
        return environment.dateFormatCreate;
    }

    get dateFormatColumn() {
        return environment.dateFormatCreate;
    }

    get submitted(): boolean {
        return this._submitted;
    }

    set submitted(value: boolean) {
        this._submitted = value;
    }

    get errorMessages(): string[] {
        if (this._errorMessages == null) {
            this._errorMessages = new Array<string>();
        }
        return this._errorMessages;
    }

    set errorMessages(value: string[]) {
        this._errorMessages = value;
    }

    get validate(): boolean {
        return this.service.validate;
    }

    set validate(value: boolean) {
        this.service.validate = value;
    }


    get activeTab(): number {
        return this._activeTab;
    }

    set activeTab(value: number) {
        this._activeTab = value;
    }


}
