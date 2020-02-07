import {Component, Input, OnDestroy, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {MobileService} from "../services/mobile.service";
import {Mobile} from "../models/mobile";
import {Location} from '@angular/common';
import {MatSnackBar} from "@angular/material";
import {takeWhile} from "rxjs/operators";

@Component({
  selector: 'app-current-detail',
  templateUrl: './current-detail.component.html',
  styleUrls: ['./current-detail.component.scss']
})
export class CurrentDetailComponent implements OnInit, OnDestroy {
  mobile: Mobile;
  private alive = true;


  constructor(private route: ActivatedRoute,
              private mobileService: MobileService,
              private location: Location, private snackBar: MatSnackBar) {
  }

  ngOnInit() {
    this.alive = true;
    this.getMobile();
  }

  ngOnDestroy(): void {
    this.alive = false;
  }

  getMobile(): void {
    const phonenumberm = +this.route.snapshot.paramMap.get('phonenumberm');
    this.mobileService.getMobile(phonenumberm).pipe(takeWhile(() => this.alive)).subscribe(mobile => this.mobile = mobile,
      (error: any) => {
        this.snackBar.open(error.error.message, 'close', {
            duration: 3000,
          }
        );
      });
  }

  goBack() {
    this.location.back();
  }

  save(): void {
    this.mobileService.updateMobileByMobile(this.mobile).pipe(takeWhile(() => this.alive))
      .subscribe(() => this.goBack(),
        (error: any) => {
          this.snackBar.open(error.error.message, 'close', {
            duration: 3000,
          });
        });
  }
}
