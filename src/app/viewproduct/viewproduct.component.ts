import { Component } from '@angular/core';
import { ProductService } from '../product.service';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-viewproduct',
  templateUrl: './viewproduct.component.html',
  styleUrls: ['./viewproduct.component.css'],
})
export class ViewproductComponent {
  updateAlert: boolean = false;
  deleteAlert: boolean = false;

  productDetails: any;

  productToUpdate = {
    email: '',
    name: '',
    phoneNumber: '',
    roleName: '',
  };
  constructor(private productService: ProductService) {
    this.getProductDetails();
  }

  //retrieve
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

  //delete
  deleteProduct(product: { email: any }) {
    this.productService.deleteProduct(product.email).subscribe(
      (resp) => {
        console.log(resp);
        this.getProductDetails();
      },
      (err) => {
        console.log(err);
      }
    );
    this.deleteAlert = true;
  }

  edit(product: any) {
    this.productToUpdate = product;
  }

  updateProduct() {
    this.productService.updateProduct(this.productToUpdate).subscribe(
      (resp) => {
        console.log(resp);
      },
      (err) => {
        console.log(err);
      }
    );
    // this.updateAlert = true;
  }

  closeAlert() {
    this.updateAlert = false;
  }

  cancelAlert() {
    this.deleteAlert = false;
  }
}
