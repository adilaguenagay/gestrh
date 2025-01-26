import {SituationFamilialeDto} from '../config/SituationFamiliale.model';
import {DiplomeDto} from '../diplome/Diplome.model';
import {FonctionDto} from '../config/Fonction.model';
import {LocalDto} from '../config/Local.model';
import {GenreDto} from '../config/Genre.model';
import {GradeDto} from '../config/Grade.model';
import {UniteStructurelleDto} from '../config/UniteStructurelle.model';

import {BaseDto} from 'src/app/zynerator/dto/BaseDto.model';


export class EmployeeDto extends BaseDto{

    public ppr: string;

    public nom: string;

    public prenom: string;

    public lieuNaissance: string;

    public photoUrl: string;

    public telephone: string;

    public address: string;

    public email: string;

    public local: LocalDto ;
    public situationFamiliale: SituationFamilialeDto ;
    public genre: GenreDto ;
    public grade: GradeDto ;
    public fonction: FonctionDto ;
    public uniteStructurelle: UniteStructurelleDto ;
     public diplome: Array<DiplomeDto>;


    constructor() {
        super();

        this.ppr = '';
        this.nom = '';
        this.prenom = '';
        this.lieuNaissance = '';
        this.photoUrl = '';
        this.telephone = '';
        this.address = '';
        this.email = '';
        this.local = new LocalDto() ;
        this.situationFamiliale = new SituationFamilialeDto() ;
        this.genre = new GenreDto() ;
        this.grade = new GradeDto() ;
        this.fonction = new FonctionDto() ;
        this.diplome = new Array<DiplomeDto>();

        }

}
