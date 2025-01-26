import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import {ToastModule} from 'primeng/toast';
import {ToolbarModule} from 'primeng/toolbar';
import {TableModule} from 'primeng/table';
import {DropdownModule} from 'primeng/dropdown';
import {InputSwitchModule} from 'primeng/inputswitch';
import {InputTextareaModule} from 'primeng/inputtextarea';
import {EditorModule} from "primeng/editor";

import { ConfirmDialogModule } from 'primeng/confirmdialog';
import { DialogModule } from 'primeng/dialog';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {CalendarModule} from 'primeng/calendar';
import {PanelModule} from 'primeng/panel';
import {InputNumberModule} from 'primeng/inputnumber';
import {BadgeModule} from 'primeng/badge';
import { MultiSelectModule } from 'primeng/multiselect';
import {TranslateModule} from '@ngx-translate/core';
import {FileUploadModule} from 'primeng/fileupload';
import {FullCalendarModule} from '@fullcalendar/angular';
import {CardModule} from "primeng/card";
import {TagModule} from "primeng/tag";

import { SituationFamilialeCreateAdminComponent } from './situation-familiale/create/situation-familiale-create-admin.component';
import { SituationFamilialeEditAdminComponent } from './situation-familiale/edit/situation-familiale-edit-admin.component';
import { SituationFamilialeViewAdminComponent } from './situation-familiale/view/situation-familiale-view-admin.component';
import { SituationFamilialeListAdminComponent } from './situation-familiale/list/situation-familiale-list-admin.component';
import { GradeCreateAdminComponent } from './grade/create/grade-create-admin.component';
import { GradeEditAdminComponent } from './grade/edit/grade-edit-admin.component';
import { GradeViewAdminComponent } from './grade/view/grade-view-admin.component';
import { GradeListAdminComponent } from './grade/list/grade-list-admin.component';
import { FonctionCreateAdminComponent } from './fonction/create/fonction-create-admin.component';
import { FonctionEditAdminComponent } from './fonction/edit/fonction-edit-admin.component';
import { FonctionViewAdminComponent } from './fonction/view/fonction-view-admin.component';
import { FonctionListAdminComponent } from './fonction/list/fonction-list-admin.component';
import { LocalCreateAdminComponent } from './local/create/local-create-admin.component';
import { LocalEditAdminComponent } from './local/edit/local-edit-admin.component';
import { LocalViewAdminComponent } from './local/view/local-view-admin.component';
import { LocalListAdminComponent } from './local/list/local-list-admin.component';
import { GenreCreateAdminComponent } from './genre/create/genre-create-admin.component';
import { GenreEditAdminComponent } from './genre/edit/genre-edit-admin.component';
import { GenreViewAdminComponent } from './genre/view/genre-view-admin.component';
import { GenreListAdminComponent } from './genre/list/genre-list-admin.component';
import { UniteStructurelleCreateAdminComponent } from './unite-structurelle/create/unite-structurelle-create-admin.component';
import { UniteStructurelleEditAdminComponent } from './unite-structurelle/edit/unite-structurelle-edit-admin.component';
import { UniteStructurelleViewAdminComponent } from './unite-structurelle/view/unite-structurelle-view-admin.component';
import { UniteStructurelleListAdminComponent } from './unite-structurelle/list/unite-structurelle-list-admin.component';

import { PasswordModule } from 'primeng/password';
import { InputTextModule } from 'primeng/inputtext';
import {ButtonModule} from 'primeng/button';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {RouterModule} from '@angular/router';
import {TabViewModule} from 'primeng/tabview';
import { SplitButtonModule } from 'primeng/splitbutton';
import { MessageModule } from 'primeng/message';
import {MessagesModule} from 'primeng/messages';
import {PaginatorModule} from 'primeng/paginator';



@NgModule({
  declarations: [

    SituationFamilialeCreateAdminComponent,
    SituationFamilialeListAdminComponent,
    SituationFamilialeViewAdminComponent,
    SituationFamilialeEditAdminComponent,
    GradeCreateAdminComponent,
    GradeListAdminComponent,
    GradeViewAdminComponent,
    GradeEditAdminComponent,
    FonctionCreateAdminComponent,
    FonctionListAdminComponent,
    FonctionViewAdminComponent,
    FonctionEditAdminComponent,
    LocalCreateAdminComponent,
    LocalListAdminComponent,
    LocalViewAdminComponent,
    LocalEditAdminComponent,
    GenreCreateAdminComponent,
    GenreListAdminComponent,
    GenreViewAdminComponent,
    GenreEditAdminComponent,
    UniteStructurelleCreateAdminComponent,
    UniteStructurelleListAdminComponent,
    UniteStructurelleViewAdminComponent,
    UniteStructurelleEditAdminComponent,
  ],
  imports: [
    CommonModule,
    ToastModule,
    ToolbarModule,
    TableModule,
    ConfirmDialogModule,
    DialogModule,
    PasswordModule,
    InputTextModule,
    ButtonModule,
    FormsModule,
    ReactiveFormsModule,
    RouterModule,
    SplitButtonModule,
    BrowserAnimationsModule,
    DropdownModule,
    TabViewModule,
    InputSwitchModule,
    InputTextareaModule,
    CalendarModule,
    PanelModule,
    MessageModule,
    MessagesModule,
    InputNumberModule,
    BadgeModule,
    MultiSelectModule,
    PaginatorModule,
    TranslateModule,
    FileUploadModule,
    FullCalendarModule,
    CardModule,
    EditorModule,
    TagModule,


  ],
  exports: [
  SituationFamilialeCreateAdminComponent,
  SituationFamilialeListAdminComponent,
  SituationFamilialeViewAdminComponent,
  SituationFamilialeEditAdminComponent,
  GradeCreateAdminComponent,
  GradeListAdminComponent,
  GradeViewAdminComponent,
  GradeEditAdminComponent,
  FonctionCreateAdminComponent,
  FonctionListAdminComponent,
  FonctionViewAdminComponent,
  FonctionEditAdminComponent,
  LocalCreateAdminComponent,
  LocalListAdminComponent,
  LocalViewAdminComponent,
  LocalEditAdminComponent,
  GenreCreateAdminComponent,
  GenreListAdminComponent,
  GenreViewAdminComponent,
  GenreEditAdminComponent,
  UniteStructurelleCreateAdminComponent,
  UniteStructurelleListAdminComponent,
  UniteStructurelleViewAdminComponent,
  UniteStructurelleEditAdminComponent,
  ],
})
export class ConfigAdminModule { }
