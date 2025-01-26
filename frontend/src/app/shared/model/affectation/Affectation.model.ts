import {EmployeeDto} from '../employee/Employee.model';
import {UniteStructurelleDto} from '../config/UniteStructurelle.model';

import {BaseDto} from 'src/app/zynerator/dto/BaseDto.model';


export class AffectationDto extends BaseDto{

    public employee: EmployeeDto ;
    public uniteMere: UniteStructurelleDto ;


    constructor() {
        super();


        }

}
