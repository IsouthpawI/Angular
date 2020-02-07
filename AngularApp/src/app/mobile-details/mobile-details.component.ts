import {Component, Input, OnInit} from '@angular/core';
import {Mobile} from "../models/mobile";
import {MobileService} from "../services/mobile.service";
import {MobileListComponent} from "../mobile-list/mobile-list.component";
import {MatSnackBar} from "@angular/material";

@Component({
  selector: 'app-mobile-details',
  templateUrl: './mobile-details.component.html',
  styleUrls: ['./mobile-details.component.scss']
})
export class MobileDetailsComponent implements OnInit {

  @Input() mobile: Mobile;

  constructor(private mobileService: MobileService, private listComponent: MobileListComponent, private snackBar: MatSnackBar) {
  }

  ngOnInit() {
  }

  updateFirstName(firstName: string) {
    this.mobileService.updateMobile(this.mobile.phoneNumberM,
      {
        firstName: firstName,
        lastName: this.mobile.lastName,
        address: this.mobile.address,
        phoneNumberH: this.mobile.phoneNumberH,
        added: this.mobile.added
      })
      .subscribe(
        data => {
          console.log(data);
          this.mobile = data as Mobile;
        },
        (error: any) => {
          this.snackBar.open(error.error.message, 'close', {
            duration: 3000,
          });
        }
      );
  }

  updateMobileByMobile(mobile: Mobile) {
    this.mobileService.setter(mobile);
  }

  deleteMobile() {
    this.mobileService.deleteMobile(this.mobile.phoneNumberM)
      .subscribe(
        data => {
          console.log(data);
          this.listComponent.reloadData();
        },
        (error: any) => {
          this.snackBar.open(error.error.message, 'close', {
            duration: 3000,
          });
        }
      );

  }

}
