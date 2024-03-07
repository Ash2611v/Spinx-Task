import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { ProductService } from '../product.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent {
  isSignDivVisiable: boolean = true;

  signUpObj: SignUpModel = new SignUpModel();
  loginObj: LoginModel = new LoginModel();

  constructor(private router: Router, private productService: ProductService) {}

  onRegister() {
    this.productService.addProduct(this.signUpObj).subscribe(
      (response) => {
        // Handle success response
        console.log('Product added successfully:', response);
        alert('Product added successfully');
      },
      (error) => {
        // Handle error response
        console.error('Error adding product:', error);
        alert('Error adding product');
      }
    );
  }

  onLogin() {
    this.productService.loginUser(this.loginObj).subscribe(
      (response) => {
        // Handle successful login
        alert('Login successful');
        console.log('Login successful:', response);
        this.router.navigateByUrl('/dashboard');
      },
      (error) => {
        // Handle login error
        alert('Invalid email or password');
      }
    );
  }
}

export class SignUpModel {
  name: string;
  email: string;
  password: string;

  constructor() {
    this.email = '';
    this.name = '';
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
