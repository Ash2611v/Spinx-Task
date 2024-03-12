import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from '../product.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent {
  isSignDivVisiable: boolean = true;

  signUpObj: SignUpModel = new SignUpModel();
  loginObj: LoginModel = new LoginModel();

  constructor(private UserService: UserService, private router: Router) {}

  ngOnInit(): void {
    // Check if token is present in local storage
    let token = localStorage.getItem('token');
    if (!token ) {
      // Token is not present, redirect to login page
      this.router.navigate(['/login']);
    } else {
      this.router.navigate(['/dashboard']);
    }
  }

  onRegister() {
    this.UserService.registerUser(this.signUpObj).subscribe(
      (response: any) => {
        // Handle success response
        console.log(response);
        localStorage.setItem('token', response.token);

        alert(response.message);
        let token = localStorage.getItem('token');
        if (token) {
          this.router.navigateByUrl('/login');
        }
        this.signUpObj = new SignUpModel;
        
      },
      (error) => {
        // Handle error response
        console.error('Error adding product:', error);
        alert('Error adding User');
      }
    );
  }
  

  onLogin() {
    this.UserService.loginUser(this.loginObj).subscribe(
      (response: any) => {
        // Handle successful login
        alert('Login successful');
        localStorage.setItem('token', response.token);
        console.log('Login successful:', response.token);
        this.router.navigateByUrl('/dashboard');
        this.loginObj = new LoginModel;
      },
      (error) => {
        // Handle login error
        alert('Invalid email or password');
      }
    );
  }
}

export class SignUpModel {
  username: string;
  email: string;
  password: string;

  constructor() {
    this.email = '';
    this.username = '';
    this.password = '';
  }
}

export class LoginModel {
  email: string;
  password: string;

  constructor() {
    this.email = '';
    this.password = '';
  }
}
