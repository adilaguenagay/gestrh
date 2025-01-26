import {SituationFamilialeCriteria} from '../config/SituationFamilialeCriteria.model';
import {DiplomeCriteria} from '../diplome/DiplomeCriteria.model';
import {FonctionCriteria} from '../config/FonctionCriteria.model';
import {LocalCriteria} from '../config/LocalCriteria.model';
import {GenreCriteria} from '../config/GenreCriteria.model';
import {GradeCriteria} from '../config/GradeCriteria.model';
import {UniteStructurelleCriteria} from '../config/UniteStructurelleCriteria.model';

import {BaseCriteria} from 'src/app/zynerator/criteria/BaseCriteria.model';

export class EmployeeCriteria extends BaseCriteria {

    public id: number;
    public ppr: string;
    public pprLike: string;
    public nom: string;
    public nomLike: string;
    public prenom: string;
    public prenomLike: string;
    public lieuNaissance: string;
    public lieuNaissanceLike: string;
    public photoUrl: string;
    public photoUrlLike: string;
    public telephone: string;
    public telephoneLike: string;
    public address: string;
    public addressLike: string;
    public email: string;
    public emailLike: string;
  public local: LocalCriteria ;
  public locals: Array<LocalCriteria> ;
  public situationFamiliale: SituationFamilialeCriteria ;
  public situationFamiliales: Array<SituationFamilialeCriteria> ;
  public genre: GenreCriteria ;
  public genres: Array<GenreCriteria> ;
  public grade: GradeCriteria ;
  public grades: Array<GradeCriteria> ;
  public fonction: FonctionCriteria ;
  public fonctions: Array<FonctionCriteria> ;
      public diplome: Array<DiplomeCriteria>;

}
