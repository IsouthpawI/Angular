import {Component, OnInit} from "@angular/core";
import {UserService} from "../services/user.service";
import {MatSnackBar} from "@angular/material";
import {User} from "../models/user";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})

export class RegisterComponent implements OnInit {

  user: User = new User();

  constructor(private userService: UserService, private snackBar: MatSnackBar) {
  }

  ngOnInit() {
  }

  register() {
    if (!!this.user.username && !!this.user.password) {

      //this.mobileService.createMobile(this.mobile)
      //   .subscribe(data => console.log(data), error => console.log(error));
      //


      console.log('userrr', this.user);
      this.userService.createUser(this.user).subscribe((user: any) => {
          console.log('user', user);
        },
        (error: any) => {
          this.snackBar.open(error.error.message, 'close', {
            duration: 3000,
          });
        });


      // this.mobileService.createMobile(this.mobile)
      //   .subscribe((data: any) => {
      //       console.log(data);
      //       this.personNumber = new PersonNumber(this.mobile.firstName, this.mobile.lastName, this.mobile);
      //       this.submitted = true;
      //       this.mobileService.createPersonNumber(this.personNumber, this.mobile.phoneNumberM)
      //         .subscribe(data => console.log(data), (error: any) => {
      //           this.snackBar.open(error.error.message, 'close', {
      //             duration: 3000,
      //           });
      //         });
      //       this.personNumber = new PersonNumber(this.mobile.firstName, this.mobile.lastName, this.mobile);
      //       this.mobile = new Mobile();
      //     },
      //     (error: any) => {
      //       this.snackBar.open(error.error.message, 'close', {
      //         duration: 3000,
      //       });
      //     });


      // }
    } else {
      this.snackBar.open('Write data', 'close', {
        duration: 3000,
      });
    }

  }


}
