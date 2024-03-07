import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';


@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  constructor(private router: Router) { }

  ngOnInit(): void {
  }

  logout() {
    // Here you can add logout logic, such as clearing local storage, removing tokens, etc.
    // For now, let's just navigate back to the login page
    this.router.navigate(['/login']);
  }
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

}