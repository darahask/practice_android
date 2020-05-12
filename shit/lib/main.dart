import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import 'package:sportshub/provider/provider.dart';
import 'package:sportshub/widgets/custom_app_bar.dart';
import 'package:sportshub/widgets/featured_products.dart';
import 'package:sportshub/widgets/product_card.dart';
import 'package:sportshub/widgets/search.dart';



class HomePage extends StatefulWidget {
  @override
  _HomePageState createState() => _HomePageState();
}

class _HomePageState extends State<HomePage> {

  @override
  Widget build(BuildContext context) {
    AppProvider appProvider = Provider.of<AppProvider>(context);

    return Scaffold(
      body: SafeArea(
        child: ListView(
          children: <Widget>[
//           Custom App bar
            CustomAppBar(),

//          Search Text field
            Search(),

//            featured products
            Row(
              children: <Widget>[
                Padding(
                  padding: const EdgeInsets.all(14.0),
                  child: Container(
                      alignment: Alignment.centerLeft,
                      child: new Text('Featured products')),
                ),
              ],
            ),
            FeaturedProducts(),

            Text(appProvider.featureProducts.length.toString(), style: TextStyle(color: Colors.black),),
//          recent products
            Row(
              children: <Widget>[
                Padding(
                  padding: const EdgeInsets.all(14.0),
                  child: Container(
                      alignment: Alignment.centerLeft,
                      child: new Text('Recent products')),
                ),
              ],
            ),

            ProductCard(
              brand: 'Nivia',
              name: 'Football',
              price: 24.00,
              onSale: true,
              picture: '',
            ),
          ],
        ),
      ),
    );
  }
}



import 'package:flutter/material.dart';
import 'package:sportshub/components/cart_products.dart';


class CustomAppBar extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return             Stack(
      children: <Widget>[
        Positioned(
          top: 10,
          right: 20,
          child: Align(
              alignment: Alignment.topRight,
              child: Icon(Icons.menu)),
        ),

        Positioned(
            top: 10,
            right: 60,
            child: Align(
              alignment: Alignment.topRight,
              child: Icon(Icons.shopping_cart),
            )
        ),

        Positioned(
          top: 10,

          right: 100,
          child: Align(
              alignment: Alignment.topRight,
              child: Icon(Icons.person)),
        ),

        Padding(
          padding: const EdgeInsets.all(8.0),
          child: Text('What are\nyou Shopping for?', style: TextStyle(fontSize: 30, color: Colors.black.withOpacity(0.6), fontWeight: FontWeight.w400),),
        ),
      ],
    )
    ;
  }
}




import 'package:flutter/material.dart';

class ProductCard extends StatelessWidget {
  final String name;
  final double price;
  final String picture;
  final String brand;
  final bool onSale;


  ProductCard({@required this.name,@required this.price,@required this.picture,@required this.brand,@required this.onSale});

  @override
  Widget build(BuildContext context) {
    return  Container(
      child: Row(
        children: <Widget>[
          Padding(
            padding: const EdgeInsets.all(8.0),
            child: ClipRRect(
              borderRadius: BorderRadius.circular(10),
              child: Image.asset(
                "images/products/fb.jpg",
                height: 90,
                width: 70,
                fit: BoxFit.cover,
              ),
            ),
          ),

          SizedBox(width: 10,),

          RichText(text: TextSpan(
              children: [
                TextSpan(text: '$name \n', style: TextStyle(fontSize: 20),),
                TextSpan(text: 'By: $brand \n', style: TextStyle(fontSize: 16, color: Colors.grey),),

                TextSpan(text: '\Rs${price.toString()} \t', style: TextStyle(fontSize: 24, fontWeight: FontWeight.bold),),
                TextSpan(text: 'ON SALE ' ,style: TextStyle(fontSize: 18, fontWeight: FontWeight.w400, color: Colors.red),),


              ], style: TextStyle(color: Colors.black)
          ),)
        ],
      ),
    );
  }
}



import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import 'package:sportshub/provider/provider.dart';
import 'package:sportshub/widgets/custom_app_bar.dart';
import 'package:sportshub/widgets/featured_products.dart';
import 'package:sportshub/widgets/product_card.dart';
import 'package:sportshub/widgets/search.dart';



class HomePage extends StatefulWidget {
  @override
  _HomePageState createState() => _HomePageState();
}

class _HomePageState extends State<HomePage> {

  @override
  Widget build(BuildContext context) {
    AppProvider appProvider = Provider.of<AppProvider>(context);

    return Scaffold(
      body: SafeArea(
        child: ListView(
          children: <Widget>[
//           Custom App bar
            CustomAppBar(),

//          Search Text field
            Search(),

//            featured products
            Row(
              children: <Widget>[
                Padding(
                  padding: const EdgeInsets.all(14.0),
                  child: Container(
                      alignment: Alignment.centerLeft,
                      child: new Text('Featured products')),
                ),
              ],
            ),
            FeaturedProducts(),

            Text(appProvider.featureProducts.length.toString(), style: TextStyle(color: Colors.black),),
//          recent products
            Row(
              children: <Widget>[
                Padding(
                  padding: const EdgeInsets.all(14.0),
                  child: Container(
                      alignment: Alignment.centerLeft,
                      child: new Text('Recent products')),
                ),
              ],
            ),

            ProductCard(
              brand: 'Nivia',
              name: 'Football',
              price: 24.00,
              onSale: true,
              picture: '',
            ),
          ],
        ),
      ),
    );
  }
}



import 'package:flutter/material.dart';

import 'featured_card.dart';

class FeaturedProducts extends StatefulWidget {
  @override
  _FeaturedProductsState createState() => _FeaturedProductsState();
}

class _FeaturedProductsState extends State<FeaturedProducts> {
  @override
  Widget build(BuildContext context) {
    return Container(
        height: 230,
        child: ListView.builder(
            scrollDirection: Axis.horizontal,
            itemCount: 4,
            itemBuilder: (_, index) {
              return FeaturedCard(
                name: 'Boxing Gloves',
                price: 1500.00,
                picture: '',
              );
            }));
  }
}



import '../db/product.dart';
import '../models/product.dart';
import 'package:flutter/material.dart';

class AppProvider with ChangeNotifier {
  List<Product> _featureProducts = [];
  ProductsService _productsService = ProductsService();
  AppProvider() {
    _getFeaturedProducts();
  }

//  getter
  List<Product> get featureProducts => _featureProducts;

//  methods
  void _getFeaturedProducts() async {
    _featureProducts = await _productsService.getFeaturedProducts();
    notifyListeners();
  }
}

