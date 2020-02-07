import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Mobile} from '../models/mobile';


const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
};

@Injectable({
  providedIn: 'root'
})


export class MobileService {

  private baseUrl = 'http://localhost:8080/api/mobiles';
  private mobile = new Mobile();
  private count = 3;
  private isChangeCount = false;


  constructor(private http: HttpClient) {
  }

  getMobile(id: number): Observable<Mobile> {
    return this.http.get<Mobile>(`${this.baseUrl}/get/${id}`);
  }

  createMobile(mobile: Object): Observable<Object> {
    return this.http.post(`${this.baseUrl}` + `/create`, mobile);
  }

  createPersonNumber(personNumber: Object, phonenumberm: number): Observable<Object> {
    return this.http.post(`${this.baseUrl}` + `/create/person/${phonenumberm}`, personNumber);
  }

  updateMobile(id: number, value: any): Observable<Object> {
    return this.http.put(`${this.baseUrl}/${id}`, value);
  }

  deleteMobile(id: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/${id}`, {responseType: 'text'});
  }

  getMobilesList(): Observable<any> {
    return this.http.get(`${this.baseUrl}`);
  }

  getMobilesList3(numberOfShowedMobiles: number, firstIndex: number, finishIndex: number): Observable<any> {
    return this.http.get<any>(`${this.baseUrl}/${numberOfShowedMobiles}/${firstIndex}/${finishIndex}`);
  }

  getMobilesList4(numberOfShowedMobiles: number, firstIndex: number, finishIndex: number): Observable<any> {
    let params = new HttpParams();

    if (numberOfShowedMobiles != null) {
      params = params.append('numberOfShowedMobiles', numberOfShowedMobiles.toString());
    }
    if (firstIndex != null) {
      params = params.append('firstIndex', firstIndex.toString());
    }
    if (finishIndex != null) {
      params = params.append('finishIndex', finishIndex.toString());
    }

    return this.http.get<any>(`${this.baseUrl}/list?${params}`);
  }

  getMobileByFirstName(firstName: string): Observable<any> {
    return this.http.get(`${this.baseUrl}/firstName/${firstName}`);
  }

  getMobileByParametrs(firstName: string, lastName: string, address: string, phoneNumberM: number, phoneNumberH: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/${firstName}/${lastName}/${address}/${phoneNumberM}/${phoneNumberH}`);
  }

  getMobileByParametrs2(firstName: string, lastName: string, address: string, phoneNumberM: number, phoneNumberH: number): Observable<any> {
    console.log(firstName);
    let params = new HttpParams();
    if (firstName != null) {
      params = params.append('firstname', firstName);
    }
    if (lastName != null) {
      params = params.append('lastname', lastName);
    }
    if (address != null) {
      params = params.append('address', address);
    }
    if (phoneNumberM != null) {
      params = params.append('phonenumberm', phoneNumberM.toString());
    }
    if (phoneNumberM != null) {
      params = params.append('phonenumberh', phoneNumberH.toString());
    }

    console.log(params.toString());

    return this.http.get(`${this.baseUrl}/searchMobile?${params}`);
  }

  getMobileByParametrsNewOne(firstName: string, lastName: string): Observable<any> {
    return this.http.get(`${this.baseUrl}/searchNew/${firstName}/${lastName}`);
  }


  getMobileByParametrsNewOne2(firstName: string, lastName: string): Observable<any> {
    let params = new HttpParams();
    if (firstName != null) {
      params = params.append('firstname', firstName);
    }
    if (lastName != null) {
      params = params.append('lastname', lastName);
    }
    return this.http.get(`${this.baseUrl}/searchNew/new?${params}`);
  }

  deleteAll(): Observable<any> {
    return this.http.delete(`${this.baseUrl}` + `/delete`, {responseType: 'text'});
  }

  updateMobileByMobile(mobile: Mobile): Observable<any> {
    return this.http.put(`${this.baseUrl}/${mobile.phoneNumberM}`, mobile);
  }


  setter(mobile: Mobile) {
    this.mobile = mobile;
  }

  getCount(): Observable<number> {
    return this.http.get<number>(`${this.baseUrl}/count`);
  }


  changeCount(count: number): void {
    this.count = count;
  }

  getCountOnList(): number {
    return this.count;
  }

  changedCount(): boolean {
    return this.isChangeCount;
  }

  setChangedCount(b: boolean) {
    this.isChangeCount = b;
  }


}
