import { OnInit } from '@angular/core';
import { Component } from '@angular/core';
import { LayoutService } from './service/app.layout.service';
import {RoleService} from "../zynerator/security/shared/service/Role.service";
import {AppComponent} from "../app.component";
import {AuthService} from "../zynerator/security/shared/service/Auth.service";
import {Router} from "@angular/router";
import {AppLayoutComponent} from "./app.layout.component";

@Component({
  selector: 'app-menu',
  templateUrl: './app.menu.component.html'
})
export class AppMenuComponent implements OnInit {
  model: any[];
  modelanonymous: any[];
    modelAdmin: any[];
constructor(public layoutService: LayoutService, public app: AppComponent, public appMain: AppLayoutComponent, private roleService: RoleService, private authService: AuthService, private router: Router) { }
  ngOnInit() {
    this.modelAdmin =
      [

				{
                    label: 'Pages',
                    icon: 'pi pi-fw pi-briefcase',
                    items: [
					  {
						label: 'Config Management',
						icon: 'pi pi-wallet',
						items: [
								  {
									label: 'Liste situation familiale',
									icon: 'pi pi-fw pi-plus-circle',
									routerLink: ['/app/admin/config/situation-familiale/list']
								  },
								  {
									label: 'Liste grade',
									icon: 'pi pi-fw pi-plus-circle',
									routerLink: ['/app/admin/config/grade/list']
								  },
								  {
									label: 'Liste fonction',
									icon: 'pi pi-fw pi-plus-circle',
									routerLink: ['/app/admin/config/fonction/list']
								  },
								  {
									label: 'Liste type diplome',
									icon: 'pi pi-fw pi-plus-circle',
									routerLink: ['/app/admin/diplome/type-diplome/list']
								  },
								  {
									label: 'Liste local',
									icon: 'pi pi-fw pi-plus-circle',
									routerLink: ['/app/admin/config/local/list']
								  },
								  {
									label: 'Liste genre',
									icon: 'pi pi-fw pi-plus-circle',
									routerLink: ['/app/admin/config/genre/list']
								  },
								  {
									label: 'Liste unite structurelle',
									icon: 'pi pi-fw pi-plus-circle',
									routerLink: ['/app/admin/config/unite-structurelle/list']
								  },
						]
					  },
					  {
						label: 'Employee Management',
						icon: 'pi pi-wallet',
						items: [
								  {
									label: 'Liste affectation',
									icon: 'pi pi-fw pi-plus-circle',
									routerLink: ['/app/admin/affectation/affectation/list']
								  },
								  {
									label: 'Liste employee',
									icon: 'pi pi-fw pi-plus-circle',
									routerLink: ['/app/admin/employee/employee/list']
								  },
								  {
									label: 'Liste diplome',
									icon: 'pi pi-fw pi-plus-circle',
									routerLink: ['/app/admin/diplome/diplome/list']
								  },
						]
					  },

				   {
					  label: 'Security Management',
					  icon: 'pi pi-wallet',
					  items: [
						  {
							  label: 'List User',
							  icon: 'pi pi-fw pi-plus-circle',
							  routerLink: ['/app/admin/security/user/list']
						  },
						  {
							  label: 'List Model',
							  icon: 'pi pi-fw pi-plus-circle',
							  routerLink: ['/app/admin/security/model-permission/list']
						  },
						  {
							  label: 'List Action Permission',
							  icon: 'pi pi-fw pi-plus-circle',
							  routerLink: ['/app/admin/security/action-permission/list']
						  },
					  ]
				  }
			]
	    }
    ];

        if (this.authService.authenticated) {
            if (this.authService.authenticatedUser.roleUsers) {
              this.authService.authenticatedUser.roleUsers.forEach(role => {
                  const roleName: string = this.getRole(role.role.authority);
                  this.roleService._role.next(roleName.toUpperCase());
                  eval('this.model = this.model' + this.getRole(role.role.authority));
              })
            }
        }
  }

    getRole(text){
        const [role, ...rest] = text.split('_');
        return this.upperCaseFirstLetter(rest.join(''));
    }

    upperCaseFirstLetter(word: string) {
      if (!word) { return word; }
      return word[0].toUpperCase() + word.substr(1).toLowerCase();
    }

    onMenuClick(event) {
        this.appMain.onMenuClick(event);
    }
}
