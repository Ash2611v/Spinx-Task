import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from '../product.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css'],
})
export class DashboardComponent implements OnInit {
  constructor(private userService: UserService, private router: Router) {}

  ngOnInit(): void {
    // Check if token is present in local storage
    let token = localStorage.getItem('token');
    if (!token) {
      // Token is not present or invalid, redirect to login page
      console.log('this is in the token not valid');
      this.router.navigate(['/login']);
    }
    console.log(token);
  }

  logout() {
    // Here you can add logout logic, such as clearing local storage, removing tokens, etc.
    // For now, let's just navigate back to the login page
    this.router.navigate(['/login']);
  }
  Add() {
    this.router.navigate(['/add']);
  }

  View() {
    this.router.navigate(['/view']);
  }
  alert: boolean = false;

  submit(productForm: any) {
    // Here you can perform any necessary actions with the form data
    // For now, let's just show the alert
    this.alert = true;
  }

  closeAlert() {
    this.alert = false;
  }
}
