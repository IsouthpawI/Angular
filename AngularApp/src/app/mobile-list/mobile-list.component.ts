import {Component, OnInit} from '@angular/core';
import {Observable} from "rxjs";
import {Mobile} from "../models/mobile";
import {MobileService} from "../services/mobile.service";
import {MatSnackBar} from "@angular/material";



@Component({
  selector: 'app-mobile-list',
  templateUrl: './mobile-list.component.html',
  styleUrls: ['./mobile-list.component.scss']
})
export class MobileListComponent implements OnInit {

  numberOfShowedMobiles: number = 2;
  firstIndex: number = 0;
  finishIndex: number = 1;
  count: number = 0;

  mobiles: Observable<Mobile[]>;


  constructor(private mobileService: MobileService, private snackBar: MatSnackBar) {
  }

  ngOnInit() {
    this.reloadData();
  }

  public deleteMobiles(): void {
    this.mobileService.deleteAll()
      .subscribe(
        data => {
          console.log(data);
          this.reloadData();
        },
        (error: any) => {
          this.snackBar.open(error.error.message, 'close', {
            duration: 3000,
          });
        });
  }

  reloadData() {
    this.mobiles = this.mobileService.getMobilesList4(this.numberOfShowedMobiles, this.firstIndex,
      this.finishIndex);

    console.log(this.firstIndex);
    console.log(this.finishIndex);
    console.log(this.numberOfShowedMobiles);
    console.log(this.count);
  }

  nextMobiles() {
    this.mobileService.getCount().subscribe((data: number) => {
        this.count = data;
        if (this.firstIndex + this.numberOfShowedMobiles < this.count) {
          this.firstIndex = this.firstIndex + this.numberOfShowedMobiles;
          this.finishIndex = this.finishIndex + this.numberOfShowedMobiles;
          console.log(this.firstIndex);
          console.log(this.finishIndex);
          console.log(this.numberOfShowedMobiles);
          console.log(this.count);
          this.reloadData();
        }
      },
      (error: any) => {
        this.snackBar.open(error.error.message, 'close', {
          duration: 3000,
        });
      });
  }

  previousMobiles() {
    if (this.firstIndex > 0 && this.finishIndex > this.numberOfShowedMobiles) {
      this.finishIndex = this.finishIndex - this.numberOfShowedMobiles;
      this.firstIndex = this.firstIndex - this.numberOfShowedMobiles;
      this.reloadData();
    }
    else {
      if (this.firstIndex != 0) {
        this.firstIndex = 0;
        this.finishIndex = this.numberOfShowedMobiles - 1;
        this.reloadData();
      }
    }
  }

  changeSettings(number: number) {
    this.firstIndex = 0;
    this.numberOfShowedMobiles = Number.parseInt(number.toString());
    this.finishIndex = number - 1;
    this.reloadData();
  }


}

