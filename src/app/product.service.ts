import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class ProductService {
  constructor(private http: HttpClient) {}

  API = 'http://localhost:9090';
  public addProduct(user: any) {
    return this.http.post(this.API + '/registerNewUser', user);
  }

  public loginUser(loginModel: any) {
    return this.http.post(this.API + '/login', loginModel);
  }

  public getProducts() {
    return this.http.get(this.API + '/getAllUsers');
  }
  public deleteProduct(email: any) {
    return this.http.delete(this.API + '/deleteUser/' + email);
  }

  public updateProduct(user: any) {
    return this.http.put(this.API + '/updateUser/' + user.email, user);
  }
}
