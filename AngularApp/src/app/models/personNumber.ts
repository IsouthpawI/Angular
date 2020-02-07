import {Mobile} from "./mobile";

export class PersonNumber {
  firstName: string;
  lastName: string;
  mobile: Mobile;

  constructor(firstName: string, lastName: string, mobile: Mobile) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.mobile = mobile;
  }

}
