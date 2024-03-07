import { Component } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { ProductService } from '../product.service';

@Component({
  selector: 'app-add-product',
  templateUrl: './add-product.component.html',
  styleUrls: ['./add-product.component.css'],
})
export class AddProductComponent {
  alert: boolean = false;
  productDetails: any;

  constructor(private productService: ProductService, private router: Router) {
    this.getProductDetails();
  }

  // Create
  submit(productform: NgForm) {
    this.productService.addProduct(productform.value).subscribe(
      (resp) => {
        console.log(resp);
        productform.reset();
        this.getProductDetails(); // Retrieve
      },
      (err) => {
        console.log(err);
      }
    );
    this.alert = true;
  }

  // Retrieve
  getProductDetails() {
    this.productService.getProducts().subscribe(
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
  View() {
    this.router.navigate(['/viewProduct']);
  }
}
