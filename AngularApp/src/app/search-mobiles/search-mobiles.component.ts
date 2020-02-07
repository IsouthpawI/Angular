import {Component, OnInit} from '@angular/core';
import {Mobile} from "../models/mobile";
import {MobileService} from "../services/mobile.service";
import {PersonNumber} from "../models/personNumber";
import {MatSnackBar} from "@angular/material";


@Component({
  selector: 'app-search-mobiles',
  templateUrl: './search-mobiles.component.html',
  styleUrls: ['./search-mobiles.component.scss']
})
export class SearchMobilesComponent implements OnInit {
  firstName: string;
  firstNameP: string;
  lastName: string;
  lastNameP: string;
  address: string;
  phoneNumberM: number;
  phoneNumberH: number;
  added: Date;
  mobiles: Mobile[];
  personNumbers: PersonNumber[];
  private newSearch = true;
  submitted = false;

  constructor(private dataService: MobileService, private snackBar: MatSnackBar) {
  }

  ngOnInit() {
    this.submitted = false;

  }

  private searchMobiles(firstName: string, lastName: string, address: string, phoneNumberM: number, phoneNumberH: number) {
    this.dataService.getMobileByParametrs2(firstName, lastName, address, phoneNumberM, phoneNumberH)
      .subscribe((mobiles) => {
        this.mobiles = mobiles;
        this.firstName = null;
        this.lastName = null;
        this.address = null;
        this.phoneNumberM = null;
        this.phoneNumberH = null;
      },
        (error: any) => {
          this.snackBar.open(error.error.message, 'close', {
            duration: 3000,
          });
        });
  }

  private searchMobilesNewOne() {

    console.log("Lastname: " + this.lastNameP);

    this.dataService.getMobileByParametrsNewOne2(this.firstNameP, this.lastNameP)
      .subscribe((personNumbers: PersonNumber[]) => {
          this.personNumbers = personNumbers;
          this.submitted = true;
        },
        (error: any) => {
          this.snackBar.open(error.error.message, 'close', {
            duration: 3000,
          }
          );


          console.log('ERROR', error.error.message);

        });
  }

  private researchMobiles() {
    this.dataService.getMobileByParametrs(this.firstName, this.lastName, this.address, this.phoneNumberM, this.phoneNumberH)
      .subscribe(mobiles => this.mobiles = mobiles, (error: any) => {
        this.snackBar.open(error.error.message, 'close', {
          duration: 3000,
        });
      });
  }

  onSubmit() {
    this.searchMobiles(this.firstName, this.lastName, this.address, this.phoneNumberM, this.phoneNumberH);
    this.firstName = null;
    this.lastName = null;
    this.address = null;
    this.phoneNumberM = null;
    this.phoneNumberH = null;
  }

  onSubmitNewOne() {
    this.searchMobilesNewOne();
  }

  deleteMobile(id: number) {
    this.dataService.deleteMobile(id)
      .subscribe(
        data => {
          console.log(data);
          this.researchMobiles();
        },
        (error: any) => {
          this.snackBar.open(error.error.message, 'close', {
            duration: 3000,
          });
        });
  }

  makeNewSearch(b: boolean) {
    this.newSearch = b;
  }

  changeSubmit() {
    this.submitted = false;
    this.firstNameP = null;
    this.lastNameP = null;
  }
}
