import {Component, OnInit} from '@angular/core';


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
import {ConfirmationService, MessageService,MenuItem} from 'primeng/api';
import {FileTempDto} from 'src/app/zynerator/dto/FileTempDto.model';


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
  selector: 'app-employee-view-admin',
  templateUrl: './employee-view-admin.component.html'
})
export class EmployeeViewAdminComponent implements OnInit {


	protected _submitted = false;
    protected _errorMessages = new Array<string>();

    protected datePipe: DatePipe;
    protected messageService: MessageService;
    protected confirmationService: ConfirmationService;
    protected roleService: RoleService;
    protected router: Router;
    protected stringUtilService: StringUtilService;


    diplome = new DiplomeDto();
    diplomes: Array<DiplomeDto> = [];

    constructor(private service: EmployeeAdminService, private typeDiplomeService: TypeDiplomeAdminService, private situationFamilialeService: SituationFamilialeAdminService, private diplomeService: DiplomeAdminService, private fonctionService: FonctionAdminService, private localService: LocalAdminService, private genreService: GenreAdminService, private gradeService: GradeAdminService, private uniteStructurelleService: UniteStructurelleAdminService){
		this.datePipe = ServiceLocator.injector.get(DatePipe);
        this.messageService = ServiceLocator.injector.get(MessageService);
        this.confirmationService = ServiceLocator.injector.get(ConfirmationService);
        this.roleService = ServiceLocator.injector.get(RoleService);
        this.router = ServiceLocator.injector.get(Router);
        this.stringUtilService = ServiceLocator.injector.get(StringUtilService);
	}

    ngOnInit(): void {
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

    public hideViewDialog() {
        this.viewDialog = false;
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

    get dateFormat(){
        return environment.dateFormatView;
    }

    get dateFormatColumn(){
        return environment.dateFormatList;
    }


}
