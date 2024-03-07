import { Component } from '@angular/core';
import { ProductService } from './product.service';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'dashboard';
  
  constructor(private router:Router){}
  Add() {
    this.router.navigate(['/addProduct'])}
  
  View() {
  this.router.navigate(['/viewProduct'])}
alert: boolean = false;

  submit(productForm: any) {
    // Here you can perform any necessary actions with the form data
    // For now, let's just show the alert
    this.alert = true;
  }

  closeAlert() {
    this.alert = false;
  }
  logout() {
    // Here you can add logout logic, such as clearing local storage, removing tokens, etc.
    // For now, let's just navigate back to the login page and show an alert
    this.router.navigate(['/login']);
    alert('Logout successful');
  }
  
}
