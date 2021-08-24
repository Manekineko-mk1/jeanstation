import { Product } from '../model/Product';

export class Cart {
    id?:string;
    priceTotalBeforeTax?:number;
    priceTotalAfterTax?:number;
    cartItems?:Product[];
}
