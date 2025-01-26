import {EmployeeCriteria} from '../employee/EmployeeCriteria.model';
import {TypeDiplomeCriteria} from './TypeDiplomeCriteria.model';

import {BaseCriteria} from 'src/app/zynerator/criteria/BaseCriteria.model';

export class DiplomeCriteria extends BaseCriteria {

    public id: number;
     public anneeObtention: number;
     public anneeObtentionMin: number;
     public anneeObtentionMax: number;
    public specialie: string;
    public specialieLike: string;
    public organisme: string;
    public organismeLike: string;
  public type: TypeDiplomeCriteria ;
  public types: Array<TypeDiplomeCriteria> ;
  public employee: EmployeeCriteria ;
  public employees: Array<EmployeeCriteria> ;

}
