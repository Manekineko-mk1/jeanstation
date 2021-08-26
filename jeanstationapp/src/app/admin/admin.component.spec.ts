import { HttpClientModule } from '@angular/common/http';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ReactiveFormsModule } from '@angular/forms';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';
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

  it('addProduct() should add Product', () => {
    component.form.clearValidators();
    spyOn(service, 'addProduct').and.returnValue(of(product1));
    component.addProduct();
    expect(component.message).toEqual('Product added');
  });

});
