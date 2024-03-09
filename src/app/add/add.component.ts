import { Component } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { UserService } from '../product.service';

@Component({
  selector: 'app-add',
  templateUrl: './add.component.html',
  styleUrls: ['./add.component.css'],
})
export class AddComponent {
  alert: boolean = false;
  productDetails: any;

  constructor(private UserService: UserService, private router: Router) {
    this.getDetails();
  }

  // Create
  submit(productform: NgForm) {
    this.UserService.addUser(productform.value).subscribe(
      (resp) => {
        console.log(resp);
        productform.reset();
        this.getDetails(); // Retrieve
        // Show success alert
        alert('customer added successfully');
      },
      (err) => {
        console.log(err);
        // Show error alert
        alert('Failed to add customer');
      }
    );
  }

  // Retrieve
  getDetails() {
    this.UserService.getUsers().subscribe(
      (resp) => {
        console.log(resp);
        this.productDetails = resp;
      },
      (err) => {
        console.log(err);
      }
    );
  }

  closeAlert() {
    this.alert = false;
  }

  // View
  view() {
    this.router.navigate(['/view']);
  }
}
