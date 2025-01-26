import {EmployeeDto} from '../employee/Employee.model';

import {BaseDto} from 'src/app/zynerator/dto/BaseDto.model';


export class UniteStructurelleDto extends BaseDto{

    public code: string;

    public libelle: string;

    public uniteMere: UniteStructurelleDto ;
     public employee: Array<EmployeeDto>;


    constructor() {
        super();

        this.code = '';
        this.libelle = '';
        this.uniteMere = new UniteStructurelleDto() ;
        this.employee = new Array<EmployeeDto>();

        }

}
