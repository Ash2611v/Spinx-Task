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

  constructor(private userService: UserService) {}

  
  ngOnInit() {
    this.getCustomerDetails(); // Fetch initial data when the component is initialized
    this.deleteCustomer;
    this.updateCustomer();
  }

  getCustomerDetails() {
    this.userService.getUsers().subscribe(
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
    this.userService.deleteUser(customer.email).subscribe(
      (resp: any) => {
        console.log(resp);
        // Update customerDetails array after successful deletion
        this.customerDetails = this.customerDetails.filter(
          (cust: any) => cust.email !== customer.email
        );
        this.deleteAlert = true; // Set deleteAlert to true when customer is successfully deleted
        this.getCustomerDetails();

        window.location.reload();
      },
      (err: any) => {
        window.location.reload();
        console.log(err);
      }
    );
  }
  edit(customer: any) {
    this.customerToUpdate = { ...customer }; // Make a copy of the customer to avoid modifying the original object
  }

  updateCustomer() {
    this.userService.updateUser(this.customerToUpdate).subscribe(
      (resp: any) => {
        console.log(resp);
        // Update customerDetails array after successful update
        this.getCustomerDetails(); // Fetch updated customer details
        window.location.reload();
      },
      (err: any) => {
        console.log(err);
       
      }
    );
  }

  closeAlert() {
    this.updateAlert = false;
    window.location.reload();
  }

  cancelAlert() {
    this.deleteAlert = false; // Function to close delete alert
  }
}
