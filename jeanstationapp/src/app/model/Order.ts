import { Product } from '../model/Product';

export class Order {
    id?:string;
    userId?:string;
    priceTotal?:number;
    orderItems?:Product[];
    creationDate?:string;
    deliveryDate?:string;
    status?:string;
}
