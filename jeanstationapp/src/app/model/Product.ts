import { Money } from '../model/Money';

export class Product {
    id?:string;
    name?:string;
    description?:string;
    picture?:string;
    price?:Money;
    discount?:number;
    quantity?:number;
    size?:string;
    color?:string;
    categories?:string[];
}
