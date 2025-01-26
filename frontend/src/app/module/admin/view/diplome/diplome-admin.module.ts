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

import { TypeDiplomeCreateAdminComponent } from './type-diplome/create/type-diplome-create-admin.component';
import { TypeDiplomeEditAdminComponent } from './type-diplome/edit/type-diplome-edit-admin.component';
import { TypeDiplomeViewAdminComponent } from './type-diplome/view/type-diplome-view-admin.component';
import { TypeDiplomeListAdminComponent } from './type-diplome/list/type-diplome-list-admin.component';
import { DiplomeCreateAdminComponent } from './diplome/create/diplome-create-admin.component';
import { DiplomeEditAdminComponent } from './diplome/edit/diplome-edit-admin.component';
import { DiplomeViewAdminComponent } from './diplome/view/diplome-view-admin.component';
import { DiplomeListAdminComponent } from './diplome/list/diplome-list-admin.component';

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

    TypeDiplomeCreateAdminComponent,
    TypeDiplomeListAdminComponent,
    TypeDiplomeViewAdminComponent,
    TypeDiplomeEditAdminComponent,
    DiplomeCreateAdminComponent,
    DiplomeListAdminComponent,
    DiplomeViewAdminComponent,
    DiplomeEditAdminComponent,
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
  TypeDiplomeCreateAdminComponent,
  TypeDiplomeListAdminComponent,
  TypeDiplomeViewAdminComponent,
  TypeDiplomeEditAdminComponent,
  DiplomeCreateAdminComponent,
  DiplomeListAdminComponent,
  DiplomeViewAdminComponent,
  DiplomeEditAdminComponent,
  ],
})
export class DiplomeAdminModule { }
