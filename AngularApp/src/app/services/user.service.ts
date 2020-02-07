import {HttpClient, HttpHeaders, HttpParams} from "@angular/common/http";
import {Injectable} from "@angular/core";
import {Mobile} from "../models/mobile";
import {Observable} from "rxjs";
import {User} from "../models/user";

const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
};

@Injectable({
  providedIn: 'root'
})


export class UserService {

  private baseUrl = 'http://localhost:8080/api/user';



  constructor(private http: HttpClient) {
  }


  checkUser(user: User): Observable<any> {
    return this.http.get<any>(`${this.baseUrl}/checkUser/${user.username}/${user.password}/`);
  }


  createUser(user: Object): Observable<Object> {
    return this.http.post(`${this.baseUrl}` + `/createUser`, user);
  }

}
