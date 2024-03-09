import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable, of } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  constructor(private http: HttpClient, private router: Router) {}

  API = 'http://localhost:9090';

  // Function to send authenticated requests with token
  private sendRequestWithToken(method: string, endpoint: string, data?: any) {
    // Get token from local storage
    const token = localStorage.getItem('token');

    // Check if token is present
    if (token === null) {
      // Redirect to login page
      window.location.href = '/login';
      throw new Error('No token found');
    }
    const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);

    // Send request to backend with token
    return this.http.request(method, this.API + endpoint, {
      body: data,
      headers,
    });
  }

  // Function to delete user
  public deleteUser(email: string) {
    return this.sendRequestWithToken('delete', `/delete_user/${email}`);
  }

  // Function to update user
  public updateUser(user: any) {
    return this.sendRequestWithToken('put', `/update_user/${user.email}`, user);
  }

  // Function to get list of users
  public getUsers() {
    return this.sendRequestWithToken('get', '/admin_only');
  }

  public addUser(user: any) {
    return this.sendRequestWithToken('post', '/add_user', user);
  }

  public logout() {
    localStorage.removeItem('token');
    // this.router.navigateByUrl('/login');
    return this.http.get(this.API + '/logout');
  }

  // Function to login user
  public loginUser(loginModel: any): Observable<any> {
    // Check if token is already present in local storage
    const token = localStorage.getItem('token');
    if (token) {
      // Token is present, redirect to dashboard
      this.router.navigate(['/dashboard']);
      // Return an observable with a dummy response
      console.log('hello world');
      return of({});
    } else {
      // No token present, make the login request
      console.log('token');
      return this.http.post<any>(this.API + '/login', loginModel);
    }
  }

  // Function to register new user
  public registerUser(user: any): Observable<any> {
    // Check if token is already present in local storage
    const token = localStorage.getItem('token');
    if (token) {
      // Token is present, redirect to dashboard
      this.router.navigate(['/dashboard']);
      // Return an observable with a dummy response
      return of({});
    } else {
      // No token present, make the registration request
      return this.http.post<any>(this.API + '/register', user);
    }
  }
}
