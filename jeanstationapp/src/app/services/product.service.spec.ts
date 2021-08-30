import { TestBed } from '@angular/core/testing';
import { Product } from 'src/app/model/Product';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { ProductService } from './product.service';

const product1: Product = {
  name: 'New Product',
  description: 'The description',
  //picture: 'picture',
  //price: 120,
  discount: 0.05,
  quantity: 56,
  size: 'L',
  color: 'BLACK',
  categories: ['Men', 'Jacket']
};

describe('ProductService', () => {
  let service: ProductService;
  let httpMock: HttpTestingController;
  beforeEach(() => {
    TestBed.configureTestingModule({
      imports:[HttpClientTestingModule],
      providers: [ProductService]
    });
    httpMock = TestBed.get(HttpTestingController);
    service = TestBed.inject(ProductService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should have getProduct()', () => {
    expect(service.getProduct).toBeTruthy();
  });

  it('should have addProduct()', () => {
    expect(service.addProduct).toBeTruthy();
  });

  it('should have updateProduct()', () => {
    expect(service.updateProduct).toBeTruthy();
  });

  it('should have deleteProduct()', () => {
    expect(service.deleteProduct).toBeTruthy();
  });

  it('getProduct() method should return list of products', () => {
    service.getProduct().subscribe();
    
    const req = httpMock.expectOne('http://localhost:8080/product/api/v1/products');
    expect(req.request.method).toEqual('GET');
  });

  it('addProduct() method should add Product', () => {
    service.addProduct(product1).subscribe(
      data => {
        expect(data.name).toEqual('New Product');
      }
    );
    const req = httpMock.expectOne('http://localhost:8080/product/api/v1/product');
    expect(req.request.method).toEqual('POST');
    expect(req.request.headers.get('Content-Type')).toEqual('application/json');
  });

  it('updateProduct() method should update Product', () => {
    product1.name = 'New name';
    service.updateProduct(product1).subscribe(
      data => {
        expect(data.name).toEqual('New name');
      }
    );
    const req = httpMock.expectOne('http://localhost:8080/product/api/v1/product');
    expect(req.request.method).toEqual('PUT');
    expect(req.request.headers.get('Content-Type')).toEqual('application/json');
  });

  it('deleteProduct() method should delete Product', () => {
    service.deleteProduct(product1.id).subscribe();
    const req = httpMock.expectOne('http://localhost:8080/product/api/v1/product/'+product1.id);
    expect(req.request.method).toEqual('DELETE');
  });

  afterEach( () => {
    httpMock.verify();
  });
});
