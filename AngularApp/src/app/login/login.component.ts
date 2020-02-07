import {Component, OnInit} from "@angular/core";
import {MatSnackBar} from "@angular/material";
import {UserService} from "../services/user.service";
import {User} from "../models/user";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  user: User = new User();

  constructor(private userService: UserService, private snackBar: MatSnackBar) {
  }

  ngOnInit() {
  }

  login() {
    if (!!this.user.username && !!this.user.password) {

      //this.mobileService.createMobile(this.mobile)
      //   .subscribe(data => console.log(data), error => console.log(error));
      //
      this.userService.checkUser(this.user).subscribe((loggedIn: boolean) => {
          if (loggedIn) {
            console.log('logged true');
            // pusti ego
          } else {
            console.log('logged nope');
            // net
          }
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
