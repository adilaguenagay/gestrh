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
  selector: 'app-unite-structurelle-view-admin',
  templateUrl: './unite-structurelle-view-admin.component.html'
})
export class UniteStructurelleViewAdminComponent implements OnInit {


	protected _submitted = false;
    protected _errorMessages = new Array<string>();

    protected datePipe: DatePipe;
    protected messageService: MessageService;
    protected confirmationService: ConfirmationService;
    protected roleService: RoleService;
    protected router: Router;
    protected stringUtilService: StringUtilService;


    employee = new EmployeeDto();
    employees: Array<EmployeeDto> = [];

    constructor(private service: UniteStructurelleAdminService, private employeeService: EmployeeAdminService, private situationFamilialeService: SituationFamilialeAdminService, private fonctionService: FonctionAdminService, private localService: LocalAdminService, private genreService: GenreAdminService, private gradeService: GradeAdminService){
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

    get viewDialog(): boolean {
        return this.service.viewDialog;
    }

    set viewDialog(value: boolean) {
        this.service.viewDialog = value;
    }

    get criteria(): UniteStructurelleCriteria {
        return this.service.criteria;
    }

    set criteria(value: UniteStructurelleCriteria) {
        this.service.criteria = value;
    }

    get dateFormat(){
        return environment.dateFormatView;
    }

    get dateFormatColumn(){
        return environment.dateFormatList;
    }


}
