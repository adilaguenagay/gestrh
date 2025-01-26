import {EmployeeCriteria} from '../employee/EmployeeCriteria.model';

import {BaseCriteria} from 'src/app/zynerator/criteria/BaseCriteria.model';

export class UniteStructurelleCriteria extends BaseCriteria {

    public id: number;
    public code: string;
    public codeLike: string;
    public libelle: string;
    public libelleLike: string;
  public uniteMere: UniteStructurelleCriteria ;
  public uniteMeres: Array<UniteStructurelleCriteria> ;
      public employee: Array<EmployeeCriteria>;

}
