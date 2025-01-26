import {Component, OnInit, Input} from '@angular/core';
import {ConfirmationService, MessageService} from 'primeng/api';

import {DatePipe} from '@angular/common';
import {Router} from '@angular/router';
import {Inject, Injectable, PLATFORM_ID} from '@angular/core';


import {environment} from 'src/environments/environment';

import {RoleService} from 'src/app/zynerator/security/shared/service/Role.service';
import {StringUtilService} from 'src/app/zynerator/util/StringUtil.service';
import {ServiceLocator} from 'src/app/zynerator/service/ServiceLocator';




import {DiplomeAdminService} from 'src/app/shared/service/admin/diplome/DiplomeAdmin.service';
import {DiplomeDto} from 'src/app/shared/model/diplome/Diplome.model';
import {DiplomeCriteria} from 'src/app/shared/criteria/diplome/DiplomeCriteria.model';
import {EmployeeDto} from 'src/app/shared/model/employee/Employee.model';
import {EmployeeAdminService} from 'src/app/shared/service/admin/employee/EmployeeAdmin.service';
import {TypeDiplomeDto} from 'src/app/shared/model/diplome/TypeDiplome.model';
import {TypeDiplomeAdminService} from 'src/app/shared/service/admin/diplome/TypeDiplomeAdmin.service';
@Component({
  selector: 'app-diplome-create-admin',
  templateUrl: './diplome-create-admin.component.html'
})
export class DiplomeCreateAdminComponent  implements OnInit {

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

	constructor(private service: DiplomeAdminService , private employeeService: EmployeeAdminService, private typeDiplomeService: TypeDiplomeAdminService, @Inject(PLATFORM_ID) private platformId? ) {
        this.datePipe = ServiceLocator.injector.get(DatePipe);
        this.messageService = ServiceLocator.injector.get(MessageService);
        this.confirmationService = ServiceLocator.injector.get(ConfirmationService);
        this.roleService = ServiceLocator.injector.get(RoleService);
        this.router = ServiceLocator.injector.get(Router);
        this.stringUtilService = ServiceLocator.injector.get(StringUtilService);
    }

    ngOnInit(): void {
        this.typeDiplomeService.findAll().subscribe((data) => this.types = data);
        this.employeeService.findAll().subscribe((data) => this.employees = data);
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
                this.item = new DiplomeDto();
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



    public async openCreateType(type: string) {
    const isPermistted = await this.roleService.isPermitted('TypeDiplome', 'add');
    if(isPermistted) {
         this.type = new TypeDiplomeDto();
         this.createTypeDialog = true;
    }else{
        this.messageService.add({
        severity: 'error', summary: 'erreur', detail: 'problème de permission'
        });
     }
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




    get validEmployeePpr(): boolean {
        return this._validEmployeePpr;
    }
    set validEmployeePpr(value: boolean) {
        this._validEmployeePpr = value;
    }


    get items(): Array<DiplomeDto> {
        return this.service.items;
    }

    set items(value: Array<DiplomeDto>) {
        this.service.items = value;
    }

    get item(): DiplomeDto {
        return this.service.item;
    }

    set item(value: DiplomeDto) {
        this.service.item = value;
    }

    get createDialog(): boolean {
        return this.service.createDialog;
    }

    set createDialog(value: boolean) {
        this.service.createDialog = value;
    }

    get criteria(): DiplomeCriteria {
        return this.service.criteria;
    }

    set criteria(value: DiplomeCriteria) {
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
