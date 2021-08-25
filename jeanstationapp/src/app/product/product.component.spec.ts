import { HttpClientModule } from '@angular/common/http';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ReactiveFormsModule } from '@angular/forms';
import { RouterTestingModule } from '@angular/router/testing';
import { ProductService } from '../services/product.service';

import { ProductComponent } from './product.component';

describe('ProductComponent', () => {
  let component: ProductComponent;
  let fixture: ComponentFixture<ProductComponent>;
  let service: ProductService;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports : [HttpClientModule, ReactiveFormsModule, RouterTestingModule],
      declarations: [ ProductComponent ],
      providers: [ProductService]
    })
    .compileComponents();
    service = TestBed.get(ProductService);
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ProductComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('ngOninit() should exist', () => {
    expect(component.ngOnInit).toBeTruthy();
  });

  it('getProducts() should exist', () => {
    expect(component.getProducts).toBeTruthy();
  });

  it('getProducts() should called getProduct', () => {
    spyOn(service, 'getProduct').and.callThrough();
    component.getProducts();
    expect(service.getProduct).toHaveBeenCalled();
  })
});
