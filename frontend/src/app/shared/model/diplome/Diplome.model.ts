import {EmployeeDto} from '../employee/Employee.model';
import {TypeDiplomeDto} from './TypeDiplome.model';

import {BaseDto} from 'src/app/zynerator/dto/BaseDto.model';


export class DiplomeDto extends BaseDto{

    public anneeObtention: null | number;

    public specialie: string;

    public organisme: string;

    public type: TypeDiplomeDto ;
    public employee: EmployeeDto ;


    constructor() {
        super();

        this.anneeObtention = null;
        this.specialie = '';
        this.organisme = '';
        this.type = new TypeDiplomeDto() ;
        this.employee = new EmployeeDto() ;

        }

}
