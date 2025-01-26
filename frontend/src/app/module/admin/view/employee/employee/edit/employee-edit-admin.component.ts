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




import {EmployeeAdminService} from 'src/app/shared/service/admin/employee/EmployeeAdmin.service';
import {EmployeeDto} from 'src/app/shared/model/employee/Employee.model';
import {EmployeeCriteria} from 'src/app/shared/criteria/employee/EmployeeCriteria.model';


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
  selector: 'app-employee-edit-admin',
  templateUrl: './employee-edit-admin.component.html'
})
export class EmployeeEditAdminComponent implements OnInit {

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

    protected diplomeIndex = -1;

    private _diplomeElement = new DiplomeDto();

    private _validEmployeePpr = true;

    private _validLocalCode = true;
    private _validLocalLibelle = true;
    private _validSituationFamilialeCode = true;
    private _validSituationFamilialeLibelle = true;
    private _validGenreCode = true;
    private _validGenreLibelle = true;
    private _validGradeCode = true;
    private _validGradeLibelle = true;
    private _validFonctionCode = true;
    private _validFonctionLibelle = true;
    private _validUniteStructurelleCode = true;



    constructor(private service: EmployeeAdminService , private typeDiplomeService: TypeDiplomeAdminService, private situationFamilialeService: SituationFamilialeAdminService, private diplomeService: DiplomeAdminService, private fonctionService: FonctionAdminService, private localService: LocalAdminService, private genreService: GenreAdminService, private gradeService: GradeAdminService, private uniteStructurelleService: UniteStructurelleAdminService, @Inject(PLATFORM_ID) private platformId?) {
        this.datePipe = ServiceLocator.injector.get(DatePipe);
        this.messageService = ServiceLocator.injector.get(MessageService);
        this.confirmationService = ServiceLocator.injector.get(ConfirmationService);
        this.roleService = ServiceLocator.injector.get(RoleService);
        this.router = ServiceLocator.injector.get(Router);
        this.stringUtilService = ServiceLocator.injector.get(StringUtilService);
    }

    ngOnInit(): void {
        this.diplomeElement.type = new TypeDiplomeDto();
        this.typeDiplomeService.findAll().subscribe((data) => this.types = data);

        this.localService.findAll().subscribe((data) => this.locals = data);
        this.situationFamilialeService.findAll().subscribe((data) => this.situationFamiliales = data);
        this.genreService.findAll().subscribe((data) => this.genres = data);
        this.gradeService.findAll().subscribe((data) => this.grades = data);
        this.fonctionService.findAll().subscribe((data) => this.fonctions = data);
        this.uniteStructurelleService.findAll().subscribe((data) => this.uniteStructurelles = data);
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
            this.item = new EmployeeDto();
        } , error =>{
            console.log(error);
        });
    }

    public hideEditDialog() {
        this.editDialog = false;
        this.setValidation(true);
    }





    public validateDiplome(){
        this.errorMessages = new Array();
    }

    public setValidation(value: boolean){
        this.validEmployeePpr = value;
    }

    public addDiplome() {
        if( this.item.diplome == null )
            this.item.diplome = new Array<DiplomeDto>();

       this.validateDiplome();

       if (this.errorMessages.length === 0) {
            if (this.diplomeIndex == -1){
                this.item.diplome.push({... this.diplomeElement});
            }else {
                this.item.diplome[this.diplomeIndex] =this.diplomeElement;
            }
              this.diplomeElement = new DiplomeDto();
              this.diplomeIndex = -1;
       }else{
           this.messageService.add({severity: 'error',summary: 'Erreurs',detail: 'Merci de corrigé les erreurs suivant : ' + this.errorMessages});
       }
    }

    public deleteDiplome(p: DiplomeDto, index: number) {
        this.item.diplome.splice(index, 1);
    }

    public editDiplome(p: DiplomeDto, index: number) {
        this.diplomeElement = {... p};
        this.diplomeIndex = index;
        this.activeTab = 0;
    }

    public validateForm(): void{
        this.errorMessages = new Array<string>();
        this.validateEmployeePpr();
    }

    public validateEmployeePpr(){
        if (this.stringUtilService.isEmpty(this.item.ppr)) {
            this.errorMessages.push('Ppr non valide');
            this.validEmployeePpr = false;
        } else {
            this.validEmployeePpr = true;
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
    get type(): TypeDiplomeDto {
        return this.typeDiplomeService.item;
    }
    set type(value: TypeDiplomeDto) {
        this.typeDiplomeService.item = value;
    }
    get types(): Array<TypeDiplomeDto> {
        return this.typeDiplomeService.items;
    }
    set types(value: Array<TypeDiplomeDto>) {
        this.typeDiplomeService.items = value;
    }
    get createTypeDialog(): boolean {
        return this.typeDiplomeService.createDialog;
    }
    set createTypeDialog(value: boolean) {
        this.typeDiplomeService.createDialog= value;
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
    get uniteStructurelle(): UniteStructurelleDto {
        return this.uniteStructurelleService.item;
    }
    set uniteStructurelle(value: UniteStructurelleDto) {
        this.uniteStructurelleService.item = value;
    }
    get uniteStructurelles(): Array<UniteStructurelleDto> {
        return this.uniteStructurelleService.items;
    }
    set uniteStructurelles(value: Array<UniteStructurelleDto>) {
        this.uniteStructurelleService.items = value;
    }
    get createUniteStructurelleDialog(): boolean {
        return this.uniteStructurelleService.createDialog;
    }
    set createUniteStructurelleDialog(value: boolean) {
        this.uniteStructurelleService.createDialog= value;
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

    get diplomeElement(): DiplomeDto {
        if( this._diplomeElement == null )
            this._diplomeElement = new DiplomeDto();
         return this._diplomeElement;
    }

    set diplomeElement(value: DiplomeDto) {
        this._diplomeElement = value;
    }

    get validEmployeePpr(): boolean {
        return this._validEmployeePpr;
    }
    set validEmployeePpr(value: boolean) {
        this._validEmployeePpr = value;
    }

    get validLocalCode(): boolean {
        return this._validLocalCode;
    }
    set validLocalCode(value: boolean) {
        this._validLocalCode = value;
    }
    get validLocalLibelle(): boolean {
        return this._validLocalLibelle;
    }
    set validLocalLibelle(value: boolean) {
        this._validLocalLibelle = value;
    }
    get validSituationFamilialeCode(): boolean {
        return this._validSituationFamilialeCode;
    }
    set validSituationFamilialeCode(value: boolean) {
        this._validSituationFamilialeCode = value;
    }
    get validSituationFamilialeLibelle(): boolean {
        return this._validSituationFamilialeLibelle;
    }
    set validSituationFamilialeLibelle(value: boolean) {
        this._validSituationFamilialeLibelle = value;
    }
    get validGenreCode(): boolean {
        return this._validGenreCode;
    }
    set validGenreCode(value: boolean) {
        this._validGenreCode = value;
    }
    get validGenreLibelle(): boolean {
        return this._validGenreLibelle;
    }
    set validGenreLibelle(value: boolean) {
        this._validGenreLibelle = value;
    }
    get validGradeCode(): boolean {
        return this._validGradeCode;
    }
    set validGradeCode(value: boolean) {
        this._validGradeCode = value;
    }
    get validGradeLibelle(): boolean {
        return this._validGradeLibelle;
    }
    set validGradeLibelle(value: boolean) {
        this._validGradeLibelle = value;
    }
    get validFonctionCode(): boolean {
        return this._validFonctionCode;
    }
    set validFonctionCode(value: boolean) {
        this._validFonctionCode = value;
    }
    get validFonctionLibelle(): boolean {
        return this._validFonctionLibelle;
    }
    set validFonctionLibelle(value: boolean) {
        this._validFonctionLibelle = value;
    }
    get validUniteStructurelleCode(): boolean {
        return this._validUniteStructurelleCode;
    }
    set validUniteStructurelleCode(value: boolean) {
        this._validUniteStructurelleCode = value;
    }

	get items(): Array<EmployeeDto> {
        return this.service.items;
    }

    set items(value: Array<EmployeeDto>) {
        this.service.items = value;
    }

    get item(): EmployeeDto {
        return this.service.item;
    }

    set item(value: EmployeeDto) {
        this.service.item = value;
    }

    get editDialog(): boolean {
        return this.service.editDialog;
    }

    set editDialog(value: boolean) {
        this.service.editDialog = value;
    }

    get criteria(): EmployeeCriteria {
        return this.service.criteria;
    }

    set criteria(value: EmployeeCriteria) {
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
