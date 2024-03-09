import { Component, OnInit } from '@angular/core';
import { UserService } from '../product.service';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-viewcustomer',
  templateUrl: './view.component.html',
  styleUrls: ['./view.component.css'],
})
export class ViewComponent implements OnInit {
  updateAlert: boolean = false;
  deleteAlert: boolean = false;

  customerDetails: any;

  customerToUpdate = {
    email: '',
    username: '',
    phoneNumber: '',
    role: '',
  };

  constructor(private UserService: UserService) {}

  ngOnInit() {
    this.getCustomerDetails(); // Fetch initial data when the component is initialized
  }

  getCustomerDetails() {
    this.UserService.getUsers().subscribe(
      (resp: any) => {
        console.log(resp);
        this.customerDetails = resp;
      },
      (err: any) => {
        console.log(err);
      }
    );
  }

  deleteCustomer(customer: { email: any }) {
    this.UserService.deleteUser(customer.email).subscribe(
      (resp: any) => {
        console.log(resp);
        // Update customerDetails array after successful deletion
        this.customerDetails = this.customerDetails.filter(
          (cust: any) => cust.email !== customer.email
        );
        this.deleteAlert = true; // Set deleteAlert to true when customer is successfully deleted
      },
      (err: any) => {
        console.log(err);
      }
    );
  }

  edit(customer: any) {
    this.customerToUpdate = customer;
  }

  updateCustomer() {
    this.UserService.updateUser(this.customerToUpdate).subscribe(
      (resp: any) => {
        console.log(resp);
        // Update customerDetails array after successful update
        const index = this.customerDetails.findIndex(
          (cust: any) => cust.email === this.customerToUpdate.email
        );
        if (index !== -1) {
          this.customerDetails[index] = this.customerToUpdate;
        }
      },
      (err: any) => {
        console.log(err);
      }
    );
  }

  closeAlert() {
    this.updateAlert = false;
  }

  cancelAlert() {
    this.deleteAlert = false; // Function to close delete alert
  }
}
