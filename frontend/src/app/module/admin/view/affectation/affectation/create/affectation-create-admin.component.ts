import {Component, OnInit, Input} from '@angular/core';
import {ConfirmationService, MessageService} from 'primeng/api';

import {DatePipe} from '@angular/common';
import {Router} from '@angular/router';
import {Inject, Injectable, PLATFORM_ID} from '@angular/core';


import {environment} from 'src/environments/environment';

import {RoleService} from 'src/app/zynerator/security/shared/service/Role.service';
import {StringUtilService} from 'src/app/zynerator/util/StringUtil.service';
import {ServiceLocator} from 'src/app/zynerator/service/ServiceLocator';




import {AffectationAdminService} from 'src/app/shared/service/admin/affectation/AffectationAdmin.service';
import {AffectationDto} from 'src/app/shared/model/affectation/Affectation.model';
import {AffectationCriteria} from 'src/app/shared/criteria/affectation/AffectationCriteria.model';
import {EmployeeDto} from 'src/app/shared/model/employee/Employee.model';
import {EmployeeAdminService} from 'src/app/shared/service/admin/employee/EmployeeAdmin.service';
import {UniteStructurelleDto} from 'src/app/shared/model/config/UniteStructurelle.model';
import {UniteStructurelleAdminService} from 'src/app/shared/service/admin/config/UniteStructurelleAdmin.service';
@Component({
  selector: 'app-affectation-create-admin',
  templateUrl: './affectation-create-admin.component.html'
})
export class AffectationCreateAdminComponent  implements OnInit {

	protected _submitted = false;
    protected _errorMessages = new Array<string>();

    protected datePipe: DatePipe;
    protected messageService: MessageService;
    protected confirmationService: ConfirmationService;
    protected roleService: RoleService;
    protected router: Router;
    protected stringUtilService: StringUtilService;
    private _activeTab = 0;



    private _validEmployeePpr = true;
    private _validUniteMereCode = true;

	constructor(private service: AffectationAdminService , private employeeService: EmployeeAdminService, private uniteStructurelleService: UniteStructurelleAdminService, @Inject(PLATFORM_ID) private platformId? ) {
        this.datePipe = ServiceLocator.injector.get(DatePipe);
        this.messageService = ServiceLocator.injector.get(MessageService);
        this.confirmationService = ServiceLocator.injector.get(ConfirmationService);
        this.roleService = ServiceLocator.injector.get(RoleService);
        this.router = ServiceLocator.injector.get(Router);
        this.stringUtilService = ServiceLocator.injector.get(StringUtilService);
    }

    ngOnInit(): void {
        this.employeeService.findAll().subscribe((data) => this.employees = data);
        this.uniteStructurelleService.findAll().subscribe((data) => this.uniteMeres = data);
    }



    public save(): void {
        this.submitted = true;
        this.validateForm();
        if (this.errorMessages.length === 0) {
            this.saveWithShowOption(false);
        } else {
            this.messageService.add({severity: 'error',summary: 'Erreurs',detail: 'Merci de corrigé les erreurs sur le formulaire'});
        }
    }

    public saveWithShowOption(showList: boolean) {
        this.service.save().subscribe(item => {
            if (item != null) {
                this.items.push({...item});
                this.createDialog = false;
                this.submitted = false;
                this.item = new AffectationDto();
            } else {
                this.messageService.add({severity: 'error', summary: 'Erreurs', detail: 'Element existant'});
            }

        }, error => {
            console.log(error);
        });
    }


    public hideCreateDialog() {
        this.createDialog = false;
        this.setValidation(true);
    }





    public  setValidation(value: boolean){
    }



    public  validateForm(): void{
        this.errorMessages = new Array<string>();
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
    get createEmployeeDialog(): boolean {
        return this.employeeService.createDialog;
    }
    set createEmployeeDialog(value: boolean) {
        this.employeeService.createDialog= value;
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
    get createUniteMereDialog(): boolean {
        return this.uniteStructurelleService.createDialog;
    }
    set createUniteMereDialog(value: boolean) {
        this.uniteStructurelleService.createDialog= value;
    }




    get validEmployeePpr(): boolean {
        return this._validEmployeePpr;
    }
    set validEmployeePpr(value: boolean) {
        this._validEmployeePpr = value;
    }
    get validUniteMereCode(): boolean {
        return this._validUniteMereCode;
    }
    set validUniteMereCode(value: boolean) {
        this._validUniteMereCode = value;
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

    get createDialog(): boolean {
        return this.service.createDialog;
    }

    set createDialog(value: boolean) {
        this.service.createDialog = value;
    }

    get criteria(): AffectationCriteria {
        return this.service.criteria;
    }

    set criteria(value: AffectationCriteria) {
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
