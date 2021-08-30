import { Product } from '../model/Product';

export class Order {
    id?:string;
    userId?:string;
    priceTotalBeforeTax?:number;
    priceTotalAfterTax?:number;
    orderItems?:Product[];
    creationDate?:string;
    deliveryDate?:string;
    status?:string;
}
