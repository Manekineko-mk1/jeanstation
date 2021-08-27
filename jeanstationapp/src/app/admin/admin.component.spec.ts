import { HttpClientModule } from '@angular/common/http';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ReactiveFormsModule } from '@angular/forms';
import { RouterTestingModule } from '@angular/router/testing';
import { of, throwError } from 'rxjs';
import { Product } from '../model/Product';
import { ApprouteService } from '../services/approute.service';
import { ProductService } from '../services/product.service';

import { AdminComponent } from './admin.component';

const product1: Product = {
  name: 'New Product',
  description: 'The description',
  //picture: 'picture',
  price: 120,
  discount: 0.05,
  quantity: 56,
  size: 'L',
  color: 'BLACK',
  categories: ['Men', 'Jacket']
};

describe('AdminComponent', () => {
  let component: AdminComponent;
  let fixture: ComponentFixture<AdminComponent>;
  let service: ProductService;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports : [HttpClientModule, ReactiveFormsModule, RouterTestingModule],
      declarations: [ AdminComponent ],
      providers: [ProductService, ApprouteService]
    })
    .compileComponents();
    service = TestBed.get(ProductService);
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should have ngOninit()', () => {
    expect(component.ngOnInit).toBeTruthy();
  });

  it('ngOninit() should call getProducts', () => {
    spyOn(component, 'getProducts').and.callThrough();
    component.ngOnInit();
    expect(component.getProducts).toHaveBeenCalled();
  })

  it('should have getProducts()', () => {
    expect(component.getProducts).toBeTruthy();
  });

  it('getProducts() should return list of product', () => {
    const list:Product[] = [
      product1
    ];
    spyOn(service, 'getProduct').and.returnValue(of(list));
    component.getProducts();
    expect(component.products).toEqual(list);
  });

  it('should have addProduct()', () => {
    expect(component.addProduct).toBeTruthy();
  });

  it('addProduct() should print error message if input is invalid', () => {
    component.addProduct();
    expect(component.message).toEqual('The fields should not be empty!!! Please verify details');
  });

  it('addProduct() should add Product', () => {
    component.form.clearValidators();
    component.form.updateValueAndValidity();
    spyOn(service, 'addProduct').and.returnValue(of(product1));
    component.addProduct();
    expect(service.addProduct).toHaveBeenCalled();
    expect(component.message).toEqual('Product added');
  });

  it('addProduct() should print error message if failed', () => {
    component.form.clearValidators();
    component.form.updateValueAndValidity();
    spyOn(service, 'addProduct').and.returnValue(throwError({status:404}));
    component.addProduct();
    expect(service.addProduct).toHaveBeenCalled();
    expect(component.message).toEqual('Registration failed!');
  });

  it('should have updateProduct()', () => {
    expect(component.updateProduct).toBeTruthy();
  });

  it('should have deleteProduct()', () => {
    expect(component.deleteProduct).toBeTruthy();
  });

  it('deleteProduct() should delete product', () => {
    spyOn(service, 'deleteProduct').and.returnValue(of(product1));
    component.deleteProduct("id");
    expect(component.message).toEqual('Product deleted');
  });

  it('deleteProduct() should print error message if an error occurs', () => {
    spyOn(service, 'deleteProduct').and.returnValue(throwError({status:404}));
    component.deleteProduct("1");
    expect(component.message).toEqual('Failed to delete product!');
  });

  it('should have clearForm()', () => {
    expect(component.clearForm).toBeTruthy();
  });

  it('should have showUpdate()', () => {
    expect(component.showUpdate).toBeTruthy();
  });

  it('should have addCategory()', () => {
    expect(component.addCategory).toBeTruthy();
  });

  it('should have deleteCategory()', () => {
    expect(component.deleteCategory).toBeTruthy();
  });

  it('should have triggerModal()', () => {
    expect(component.triggerModal).toBeTruthy();
  });

  it('should have fileChange()', () => {
    expect(component.fileChange).toBeTruthy();
  });

});
