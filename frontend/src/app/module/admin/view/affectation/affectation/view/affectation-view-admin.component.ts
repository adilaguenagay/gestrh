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


import {AffectationAdminService} from 'src/app/shared/service/admin/affectation/AffectationAdmin.service';
import {AffectationDto} from 'src/app/shared/model/affectation/Affectation.model';
import {AffectationCriteria} from 'src/app/shared/criteria/affectation/AffectationCriteria.model';

import {EmployeeDto} from 'src/app/shared/model/employee/Employee.model';
import {EmployeeAdminService} from 'src/app/shared/service/admin/employee/EmployeeAdmin.service';
import {UniteStructurelleDto} from 'src/app/shared/model/config/UniteStructurelle.model';
import {UniteStructurelleAdminService} from 'src/app/shared/service/admin/config/UniteStructurelleAdmin.service';
@Component({
  selector: 'app-affectation-view-admin',
  templateUrl: './affectation-view-admin.component.html'
})
export class AffectationViewAdminComponent implements OnInit {


	protected _submitted = false;
    protected _errorMessages = new Array<string>();

    protected datePipe: DatePipe;
    protected messageService: MessageService;
    protected confirmationService: ConfirmationService;
    protected roleService: RoleService;
    protected router: Router;
    protected stringUtilService: StringUtilService;



    constructor(private service: AffectationAdminService, private employeeService: EmployeeAdminService, private uniteStructurelleService: UniteStructurelleAdminService){
		this.datePipe = ServiceLocator.injector.get(DatePipe);
        this.messageService = ServiceLocator.injector.get(MessageService);
        this.confirmationService = ServiceLocator.injector.get(ConfirmationService);
        this.roleService = ServiceLocator.injector.get(RoleService);
        this.router = ServiceLocator.injector.get(Router);
        this.stringUtilService = ServiceLocator.injector.get(StringUtilService);
	}

    ngOnInit(): void {
    }


    get employee(): EmployeeDto {
        return this.employeeService.item;
    }
    set employee(value: EmployeeDto) {
        this.employeeService.item = value;
    }
    get employees(): Array<EmployeeDto> {
        return this.employeeService.items;
    }
    set employees(value: Array<EmployeeDto>) {
        this.employeeService.items = value;
    }
    get uniteMere(): UniteStructurelleDto {
        return this.uniteStructurelleService.item;
    }
    set uniteMere(value: UniteStructurelleDto) {
        this.uniteStructurelleService.item = value;
    }
    get uniteMeres(): Array<UniteStructurelleDto> {
        return this.uniteStructurelleService.items;
    }
    set uniteMeres(value: Array<UniteStructurelleDto>) {
        this.uniteStructurelleService.items = value;
    }

    public hideViewDialog() {
        this.viewDialog = false;
    }

    get items(): Array<AffectationDto> {
        return this.service.items;
    }

    set items(value: Array<AffectationDto>) {
        this.service.items = value;
    }

    get item(): AffectationDto {
        return this.service.item;
    }

    set item(value: AffectationDto) {
        this.service.item = value;
    }

    get viewDialog(): boolean {
        return this.service.viewDialog;
    }

    set viewDialog(value: boolean) {
        this.service.viewDialog = value;
    }

    get criteria(): AffectationCriteria {
        return this.service.criteria;
    }

    set criteria(value: AffectationCriteria) {
        this.service.criteria = value;
    }

    get dateFormat(){
        return environment.dateFormatView;
    }

    get dateFormatColumn(){
        return environment.dateFormatList;
    }


}
