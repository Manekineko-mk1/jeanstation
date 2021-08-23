import { Product } from '../model/Product';

export class Cart {
    id?:string;
    priceTotal?:number;
    cartItems?:Product[];
}
