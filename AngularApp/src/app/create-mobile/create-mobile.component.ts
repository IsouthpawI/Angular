import {Component, OnDestroy, OnInit} from '@angular/core';
import {Mobile} from '../models/mobile';
import {MobileService} from '../services/mobile.service';
import {PersonNumber} from '../models/personNumber';
import {MatSnackBar} from '@angular/material';
import {CanComponentDeactivate} from "../guard/can-deactivate.guard";
import {Observable} from "rxjs";
import {FormControl, Validators} from "@angular/forms";
import {takeWhile} from "rxjs/operators";

@Component({
  selector: 'app-create-mobile',
  templateUrl: './create-mobile.component.html',
  styleUrls: ['./create-mobile.component.scss']
})
export class CreateMobileComponent implements OnInit, CanComponentDeactivate, OnDestroy {

  phoneNumberMCheck = new FormControl('', [Validators.required, Validators.pattern('[0-9]{9}')]);
  phoneNumberHCheck = new FormControl('', [Validators.required, Validators.pattern('[0-9]{11}')]);

  mobile: Mobile = new Mobile();
  personNumber: PersonNumber = new PersonNumber(this.mobile.firstName, this.mobile.lastName, this.mobile);
  submitted = false;
  saved = false;
  private alive = true;

  constructor(private mobileService: MobileService, private snackBar: MatSnackBar) {
  }

  ngOnInit() {
    this.alive = true;
  }

  newMobile(): void {
    this.submitted = false;
    this.mobile = new Mobile();
    this.personNumber = new PersonNumber(this.mobile.firstName, this.mobile.lastName, this.mobile);
  }

  save() {
    if (this.mobile.firstName.valueOf() !== '' && this.mobile.lastName.valueOf() !== '' && this.mobile.address.valueOf() !== ''
      && Number.parseInt(this.mobile.phoneNumberM.toString()) !== 0
      && Number.parseInt(this.mobile.phoneNumberH.toString()) !== 0 && this.mobile.added.valueOf() !== null) {
      if (this.mobile.firstName.match(/^[a-zA-Z]+$/)) {
        if (this.mobile.lastName.match(/^[a-zA-Z]+$/)) {
          if (this.mobile.address.match(/^[a-zA-Z0-9,\\.\s]+$/)) {
            if (this.mobile.phoneNumberM.toString().match(/[0-9]{9}/)) {
              if (this.mobile.phoneNumberH.toString().match(/[0-9]{11}/)) {
                this.mobileService.createMobile(this.mobile).pipe(takeWhile(() => this.alive))
                  .subscribe((data: any) => {
                      console.log(data);
                      this.personNumber = new PersonNumber(this.mobile.firstName, this.mobile.lastName, this.mobile);
                      this.submitted = true;
                      this.mobileService.createPersonNumber(this.personNumber, this.mobile.phoneNumberM).pipe(takeWhile(() => this.alive))
                        .subscribe(data => {
                          console.log(data);
                          this.saved = true;
                        }, (error: any) => {
                          this.snackBar.open(error.error.message, 'close', {
                            duration: 3000,
                          });
                        });
                      this.personNumber = new PersonNumber(this.mobile.firstName, this.mobile.lastName, this.mobile);
                      this.mobile = new Mobile();
                    },
                    (error: any) => {
                      this.snackBar.open(error.error.message, 'close', {
                        duration: 3000,
                      });
                    });
              }
            }
          }
        }
      }
    }
  }

  onSubmit() {
    this.save();
  }

  canDeactivate(): Observable<boolean> | Promise<boolean> | boolean {
    if ((!!this.mobile.firstName || !!this.mobile.lastName || !!this.mobile.address ||
      !!this.mobile.phoneNumberH || !!this.mobile.phoneNumberM)
      && !this.saved) {
      return confirm('Your changes are unsaved!! Do you like to exit');
    }
    return true;
  }

  getErrorMessageMobilePhone() {
    console.log(this.phoneNumberMCheck.errors);
    return this.phoneNumberMCheck.hasError('required') ? 'You must enter a value' :
      this.phoneNumberMCheck.hasError('pattern') ? 'Not a valid number' :
        '';
  }

  getErrorMessageHomePhone() {
    console.log(this.phoneNumberHCheck.errors);
    return this.phoneNumberHCheck.hasError('required') ? 'You must enter a value' :
      this.phoneNumberHCheck.hasError('pattern') ? 'Not a valid number' :
        '';
  }

  ngOnDestroy(): void {
    this.alive = false;
  }

}
