/*!
 *
 * Jquery Mapael - Dynamic maps jQuery plugin (based on raphael.js)
 * Requires jQuery and Mapael >=2.0.0
 *
 * Map of be
 * 
 * @author ismael cadenas
 */
(function (factory) {
    if (typeof exports === 'object') {
        // CommonJS
        module.exports = factory(require('jquery'), require('jquery-mapael'));
    } else if (typeof define === 'function' && define.amd) {
        // AMD. Register as an anonymous module.
        define(['jquery', 'mapael'], factory);
    } else {
        // Browser globals
        factory(jQuery, jQuery.mapael);
    }
}(function ($, Mapael) {

    "use strict";
    
    $.extend(true, Mapael,
        {
            maps :  {
                BE : {
                    width : 1002,
                    height : 820,
                    getCoords : function (lat, lon) {
                        // todo
                        return {"x" : lon, "y" : lat};
                    },
                    'elems': {
                        "BEL2" : "M356.7 315.5l-1.8 6.8 1.2 4.5 3.7 4.8 5.5 2 15.7 0.5 5.2-4.2 5 1.6 6-3.2 1.9 2.8 3.6-6.9 7 2.3-1.4 1.5-1 5.3 0.3 15.1 2.5 7.2 4.5 1.9 6.1-0.9 8.1-11.2 2.9-1.2 5 6.6-1.3 8.6 13.8 3.5-1.4 8.5 8.4 1.8 6.5 10.1 7.6-0.8-0.7 10.4 6.5-7.2 14.7-1.6 2.7 2.8 11.7-0.1 1.2 2.1-0.7 10.1 8.5-4.5-0.3 3.8 3.8 1.7 15.6-6.6-4.6 20.1 9.3-2.4-2 3.8 3.2 4.4-3.5 9.5-3.5 0.6 2.5 2.9-2.4 2.5 11.2 6.7-3.9 8.5-4.1 1.3 5.3 6.3-6.2 10.4 1.6 15.2-17.5 2.9-4.8-6.1-7.2 0.7-5.2 9.1-14.4-2-2.1 7.6-4.7-0.2-3.3 6-8.7 0.6-10.3 7.6 4.3 5.8 9.4 5.6 2-0.1 4.8-5.5 5.5 1.2 3.7 2.8-3.2 13 2.1 5.8-9.4 7.3 9.9 17.3-3.4 6.9 0.2 16.2 1.2 6.6 4.2 1.4 2 21 7.6 15.5-4.2 10.2-38.9-10.7-19.8 2.9-3.5-1-14.5-7.9 2.1 0.2-0.3-5.9-1.5-6.2-1.2-0.5 1.1-4.7 1.3-3.1 1.9-2.3 4.7-4.4 6-4.3 6.2-3.1 0.1-2.7-1.8-3.2-1.6-3.8-4.3-17.4-2.6-1.3-2.8 0.4-5.1 2.4-3.7-1.9 0.6-7.3 5-16.2 1.4-7.4 1.2-3.3 2.7-3.9 6.1-4.8 2.6-2.9 0.6-4.3-1.6-2.6-3.2-2.8-3.5-2.2-2.3-0.9-3.3 1.4 0.2 5.4-1.9 1.9-4.7-2.3-7.1-15.2-5.4-4.8-5.5-3.2-8.6-9.6-5-2.4-5.5 0.6-16.5 6.9-5.9 0.1-4.6-2.2-4.4-3.2-5.4-2.7-10.7-1.3-11.3 0.9-3.6 1.3-2 2.3-4.7 8-1.8 4.6-2 0.7-1 2.6-9.1-8.6-2.3-16.6 0-18.9-2.2-15.7-4.2-7.7-5.2-5-5.6-2.8-5.9-1.2-16.7-0.1-3.2-2.4 2.5-4.6 0.4-3.3-1.7-2.1-3.7-1.1-3.7 0.3-2.7 1.7-2.5 2.6-3.6 2.7-11.3 4.2-6.3 0.7-4.5-1.2-11.3-6-3.4-2.7-4.1-5.2-1.4-4.6-0.4-13-6.8-23-2.1-11.8 3.6-6.8-2.9-9.8-2.1-3.8-3.5-1.1-3.6-0.5-2.1-2.4-3.2-7.8-3.6-6.5 3.2-1.1 11.6-6 10.6 6.1 11-1.6 5.6 7.9-0.4 5.6 6.4 4.7 4 0.9 1.1-3.1 11.7-12.3 11.6-8.7 9.5 4 10.3-3.1 1.7 12.9 17.1 0.3 7.3 5 10.5-20.5 7.4-4.2 2.8 5.6 11.2-4.8 4.3 12.8 10.4-1.2 3.5 3.5 14.6-3.3 2.7 7.1z m-266.4 13.9l-1-3.9-5.3-16.9 6.9-4.2 1.7-0.5 3.7 3.4 7.4-1.5 1.1-3.8 9.8-4-5.3-6.3 0.2-1.8 15.1-6.1 4 2.6 2.4 15.1-13.5 4.3-6 3.7-4.7 5.1-7.8 13.6-3.4 2.6-5.3-1.4z",
                        "BEL3" : "M873.8 308.5l4.9 6.2-6 9.3-2.3 1-6.2-3.3-9.3 1.4-10.9-14.6-6.7 1.8-4.5-4.9 2.8-4.2 2.3-1.8 2.6 1 5.3 5.9 2.6 1.8 22.5-0.6 2.9 1z m-60.6-23.1l-12.6 12.8-22.5 6.7 0.5 6.6-7 6.2-4.1 1-5.1-5.6-2 7.2-5.8 0.6-7-2.7-3.2-8-3.5-1.6-11.9 4.1-5.2 4.6-1.2 4.6-8.3-4.5-8.3 5.2-1.9-4.7-1.9 3.9-15.4-2 2.9 6.7-3.3 4.1-17.3-5.1 5-10.8-5-3 3.5-8.3-2.1-7.5 11.7-12.2-5.7-2 1.8-13.8 4.3-12.1 5.1 1.8 1.2-1.3 2.3-11.4-3.8-3.3-9.1-0.1-0.9-3.3-10 4.8-4.3-4.8-4.3 1.9-1.7-1.3-3.4-9 10.4-9.3 0.2-9.1 10.9-5-1.7-9.6 2.5-0.7 1.4 3.3 6.5-2.7-7-9.1-5.7-2.6-7.3 1 0 10.3-4.6 2.9-5.3-3.4-5.2 2.1-4.2-8.2-6.6 1.8 7.5-10.4 0-5.7 20.7-4.1 10.5-13.4 13-0.7 5.8-8.6 8.4 2.5 6.5-5.8-0.8-16.7-10.6-9.9 2.9-15.8 3.1-2.6 8.1-2 30.6 1.3 5.4-2 10.9-7.7 5.1-2.5 5.6 0.7 6.1 3.8 4.8 6.3 1.8 8 0.9 9.4 5.9 5.1 14.6 4.4 3.6 2.5 2.4 2.9 2.9 2 4.4 0 10.8-2.3 3.4 0.4 4.2 2.5 5.4 7.5 3.5 2.9 7.9-2 4.4 0.4 3 7.2-3.7 3.6-0.8 4.7 5.9 6.4-2.4 1.6-2.6 1-2.8 0.5-2.8-0.3 1.7 5.9-1.9 5.6-1.4 2.9-2 2.7-3-2.6-2.1 1.3-1.3 4-0.6 5.5 3.5 4.8-4.1 10-10.2 16.4 5.7-1.6 2.5 0.1 2.6 1.5-3 5-4.5 9.5-3.3 5.1-1.3 1-6 2.3-18.6 20.9 0.5 9.2 4.2 6.2 4.3 3.1z",
                        "BEL3474" : "M492.8 244.9l4.1 7-0.1 3.9-4.1 4.2 10.4 6.7 4.1 13-5.5 0.7-2.1 3.6 8.6 6.4-21.1 11.3-5.5 1.3-5.7-1-7.1-4.9-6.9-14.5-4.7 2.5-9.7-5.5 3.1-6.2 6.6-1.1 1.6-6.9-2.6-4.7 4.9-9.7 9.5-4.8 11.1 1.9 6.7-6.7 4.4 3.5z",
                        "BEL3475" : "M669.1 325.5l-6.8 1.1-3.9-1.9-4.3-5.5 0.1-7.3-6.3-3.7-9.8-7.8-12.7 10.1-3.9 0.6-3.3-2.7 0.9-7.4-19.5 2 1.1-4.4-6.2-0.6-5.6-9.3-10.8-3.7-6.9 2.5-2.3 5.2-1-3.1-4.6 2.6-13.5-3.5-1.1 10.5 4.8 6.4-5 5.8-8.7 0.5-1.4-8.9-6.1 6.7-11.3 2.6-0.6 5.1-8.2-4.8-1.3-2 1.1-4.7-3.7-0.6-5.7 1.8-9.4 7.4-12.5 1.9-1.5 6.2-4.9-0.4-4.9-6.8-1.9 2.2 2.7 4.1-5.6 0.1-0.7 6.2-1.8 2.2-3.8 1.2-7.2-2.4-5 3.7-8.5-7-7 0.2-5.6-7.8-2.5-1.1-2.6 1-1.8 4.5-6.3-0.1-2.1 4.1-7-2.3-3.6 6.9-1.9-2.8-6 3.2-5-1.6-5.2 4.2-15.7-0.5-5.5-2-3.7-4.8-1.2-4.5 1.8-6.8 6 2.2 8.4-2.2 0.4-2.2-5.4-7.4 3.3-7.3 3.2-1.8 6.7 5.3 4.4-5.6 3.7 3.4 4.4-1.8 7.7-6.6 3.8-11.5-2.3-6-6.6-1.3 12.1-17.6 0.2-4.3-3.7-3.3 6.8-5.2-2.3-7.9 11.9 5 6-4.2 0.1-5.2-3.6-11.5 3.9-8.3 8.4 3 5.9-2.8 6.1-19.7 8.8-2.3 6.3 1.1 12.8 5.7-1.8 3 1.7 0.8 8.1-5.4-2.5 6.2 4.9 1.5 1.2 6.1 15.8-3.8 5.7 5.9 4.7-7.1 8.8 6.7 4.7-4.4 8.2 4.8 4 0.1 3.4-8.4 18.9-5.1 7 3.8-3 6.6 10.1-11.4 5.7 1.2 8.5-6.4 4.4 9.7 3.3 2.2 13.5-5.6 3.2 1.6 7-6.2 10-4.1 4.8-0.3 3.6 4.3 6.6-1.8 4.2 8.2 5.2-2.1 5.3 3.4 4.6-2.9 0-10.3 7.3-1 5.7 2.6 7 9.1-6.5 2.7-1.4-3.3-2.5 0.7 1.7 9.6-10.9 5-0.2 9.1-10.4 9.3 3.4 9 1.7 1.3 4.3-1.9 4.3 4.8 10-4.8 0.9 3.3 9.1 0.1 3.8 3.3-2.3 11.4-1.2 1.3-5.1-1.8-4.3 12.1-1.8 13.8 5.7 2-11.7 12.2 2.1 7.5-3.5 8.3 5 3-5 10.8z m-176.3-80.6l-4.4-3.5-6.7 6.7-11.1-1.9-9.5 4.8-4.9 9.7 2.6 4.7-1.6 6.9-6.6 1.1-3.1 6.2 9.7 5.5 4.7-2.5 6.9 14.5 7.1 4.9 5.7 1 5.5-1.3 21.1-11.3-8.6-6.4 2.1-3.6 5.5-0.7-4.1-13-10.4-6.7 4.1-4.2 0.1-3.9-4.1-7z",
                        "BEL3476" : "M638.4 352.6l-0.9 9.8 12.6 12 6.7 19.6-4.9 3.7 14.5 0.8 3.8 9.6 3.6 0.6 6.1-2.9-3.5 3.4 1.8 6.5 5.2-0.8-1.3 3.6 2.8 4.8 8.8-3.5 5.6 5.7 4.8 10.8-4 8 3.1 5.2 12.9 3.5-0.3 7.5 3.6 5 14.2-9.6 11 3.8 2.3 5.2-5.5 7.3 3.7 4.8-14.1 10.6 2 2.1 3.3-4.7 6-0.4 2.6 6.4-0.2 6.2-7.2 7.4-18.3 11.9-2.7-3.6-2.8 0.3-5.8 3.9 0.6 4.8-7.4-0.5-4.8 3.4 9.9 5.6-2.8 4 3.2 0-0.4 4.1 9.2 10.8-2.4 7.3-7.4 2.3 6.6 6.2-1.3 4.3-7-0.7-7 5-5.5 1.1-24.3 1.7-5.5-1.9-1.5-11.3-3.8 15.5 2.2 2.3-2.7 6.8-8.5 8.9-2.5 0.8-2.3-2.4-1.8 6.2-5-0.1 6.3 6.1 3.4 13.2 2.7 1.4 11.1-2.2 2.3 5.4 7.6 3.9-1 9 5.6 5.6-13 5.2-6.6 6.3 4.4 4.3-19.5 14-1.1 3.5 2.2 7.7-3.3 1-1.2 6.8-0.8 2.8-4.8 0.2-11.2 4-5.9 0.9-6.7-0.9-2.4-3.1 0.1-15-0.6-2.4-2.6-4.2-0.5-2 0.5-2.5 2.3-2.7 3.5-10.4 0.7-3.4-0.5-5.3-4-7.4-12.3-5-3.4-5.7 1.1-6.4 10-24.9 0.2-4.3-0.4-3.4 0.3-3.6 4.9-10.7 1.5-0.4 2.8 3 0.6-7.1 1.4-8.3 0.4-7.1-2.4-3.1-8.1 1.7-3-1.3-1.1-6-7 3.2-27.6 28.1-1.5 3.2-0.7 3.9 1.5 4.5 0.3 2.8-2 12.8-1.8 6.4-2.4 4.6-2.8 1.9-10.8 1.7-36.1 18-7.7 1.3-1.9-0.5 4.2-10.2-7.6-15.5-2-21-4.2-1.4-1.2-6.6-0.2-16.2 3.4-6.9-9.9-17.3 9.4-7.3-2.1-5.8 3.2-13-3.7-2.8-5.5-1.2-4.8 5.5-2 0.1-9.4-5.6-4.3-5.8 10.3-7.6 8.7-0.6 3.3-6 4.7 0.2 2.1-7.6 14.4 2 5.2-9.1 7.2-0.7 4.8 6.1 17.5-2.9-1.6-15.2 6.2-10.4-5.3-6.3 4.1-1.3 3.9-8.5-11.2-6.7 2.4-2.5-2.5-2.9 3.5-0.6 3.5-9.5-3.2-4.4 2-3.8-9.3 2.4 4.6-20.1-0.9-6.9 4.4-3.5 4.6-0.6 9.4 3.3 5.8-1.3-3-12.7 2.9-4.9 2.5 0 7.2 5.1 1.2-4.5 6.7-1.9 1.6 8.3 10.4-4.1 2.9 4.3-1-4.8 1.4-1.2 27.7-7.4 0.3-2.5 6.9 0.6 5.9-8 8.2 1.9z",
                        "BEL3477" : "M907.9 552.8l-2.3-1.2-1.5-2.9-0.9-3.9-1.7-1.8-3.9 3.2-5.6 0.6-2.2 3.7-1 5.5-2.1 5.9-3.8 4-9.7 4.7-4.3 3.9-0.9 2.6-0.5 6.6-0.6 2.7-1.6 2.3-3.8 3.3-1.6 2.5 0.3 8.5-0.6 3.2-1.9 2-5.2 2.3-1.6 2.1-0.3 5.6 1.8 2.2 1 2.3-2.8 5.5-2.1 2-3.9 0.9-1.8 1.2-2 2.8-2.6 6.6-9 15.7-1.1 3.8 3.1 1.5 5.7 4.3 2.4 3.7-6.8-0.7 0.1 2.3-1.4 3.6 2.5 2.3-3 2.1-1.2 3.8 0.4 4.8 1.6 5.1 2.7 4.5 2.6 1.3 2.8 0.5 3.3 2 1.7 2.6 6.1 12.1 0.4 1.9 0.5 6.5 1.6 1.3 6.6 2.3 3.4-0.5 1.5 2.4 0.3 3.1-2.2 3.1-0.5 4.8-1.2 2.7 0.9 2.9 1.8 1.4 5.2 0.6 1.9 1.6 1.4 5.7-1.4 3.4-4.7 4.9-3.3 9.2-2 4-3 2.8 4.4 2.4-2.4 6.2-6 6.4-6.1 2.9-3.1-0.7-5.6-3.5-2.8-0.4-2.8 1.5-3.4 4.4-3.2 1.4-5.6-1-5.5-2.7-5.9-1.4-6.4 3.3-3.1 4.2-1.4 4.5-3.9 0.5-2.2-0.6-8.1-3.8-4.9 2.2-6.5 5.2-6.7 3.9-5.6-1.5-1.6-3.5 0.3-3.6 0.8-3.7 0-4.2-1-3.7-12.5-22.6-4.8-4.4-8.1-2.9-2.9-1.8-1.6-0.3-3.4 5.3-1.9 0.6-4.3-0.8-1.2-1.6-0.7-3.7 0.8-5.6 3.3-2.8-1.8-6-5-6.2-5.4-5.4-5.6 0.2-11.7 3.2-6-1.7-4.7-3.5-3.8-3.8-11.1-16-3-3-9.3-2.6-5.1-3-9.7-9.5-4.7-2.7-0.6 0.1 0.8-2.8 1.2-6.8 3.3-1-2.2-7.7 1.1-3.5 19.5-14-4.4-4.3 6.6-6.3 13-5.2-5.6-5.6 1-9-7.6-3.9-2.3-5.4-11.1 2.2-2.7-1.4-3.4-13.2-6.3-6.1 5 0.1 1.8-6.2 2.3 2.4 2.5-0.8 8.5-8.9 2.7-6.8-2.2-2.3 3.8-15.5 1.5 11.3 5.5 1.9 24.3-1.7 5.5-1.1 7-5 7 0.7 1.3-4.3-6.6-6.2 7.4-2.3 2.4-7.3-9.2-10.8 0.4-4.1-3.2 0 2.8-4-9.9-5.6 4.8-3.4 7.4 0.5-0.6-4.8 5.8-3.9 2.8-0.3 2.7 3.6 18.3-11.9 7.2-7.4 0.2-6.2-2.6-6.4-6 0.4-3.3 4.7-2-2.1 14.1-10.6-3.7-4.8 5.5-7.3-2.3-5.2 8.6-8.2-5.2-9.2 1.6-2.9 3.5-0.2 11.2 8.9 10.4-6.9 2.4 8 4.3 0.9-0.8 3.6 1.5 1.3 4.3 0.7 8.8-4.2 14 13.2 8.6-0.6-1.2 4.9 13.1 0-4.5 10.1 0.2 4.5 4.2 7.1 7 1.7-7 14.9 15.6-2.4 4.3 1.4 1.7 3.8 1.7-3.9 16.4-2.3 1.7-4-9-1.3 5.7-14.1-4.8-4.1 2.3-7.5 31.4 6.9 0.5 3.5 8.3 4.3-1.5 37.7 1.2 4.5 5.6 1.1-3.6 4.1 1.3 5.3-0.5 12.5z",
                        "BEL3478" : "M461.3 95.1l-1 0.7-1.6 4.1 5.5 17.8-2.3 1.5 6.3 9 2 8.2-1.2 9-3.2 6.5-10.3 3.4-10.5 0-5.2 1-4.6 3.2-3.3 3.9-0.8 1.8 1.4 9.3 1.5 4.6 0.8 4.8-1.3 4.1 2.5 3 2.9 1.3 5.1-4.2 2.2 2.6-6.1 19.7-5.9 2.8-8.4-3-3.9 8.3 3.6 11.5-0.1 5.2-6 4.2-11.9-5 2.3 7.9-6.8 5.2 3.7 3.3-0.2 4.3-12.1 17.6 6.6 1.3 2.3 6-3.8 11.5-7.7 6.6-4.4 1.8-3.7-3.4-4.4 5.6-6.7-5.3-3.2 1.8-3.3 7.3 5.4 7.4-0.4 2.2-8.4 2.2-6-2.2-2.7-7.1-14.6 3.3-3.5-3.5-10.4 1.2-4.3-12.8-11.2 4.8-2.8-5.6-7.4 4.2-10.5 20.5-7.3-5-17.1-0.3-1.7-12.9-10.3 3.1-9.5-4 16.2-12 0.6-3-6-8.2 3.4-4.2-16-13.9 6.1-6.3-2.1-7.7-4-1.7-6 3.7-2.2-5.6 3.5-3.5-2-4.7 6.1 0.9 1.8-1.8-3.9-4 5.2 0.1-12.5-6.2 8-3.7-4.4-6 6.8-10.1-7.8-13.4 5.6-4-29.3-21 11.4-7.4 9.3-17.8-6.1-10.8-7-2.3 6.3-4.4-4.5-10.8 8.6-5.4-0.3-0.8 6 2.8 8.2 0.9 12.3-1.9 0.8-3.9-0.7-5.3 0.5-4.7 2.8-3 3.8-0.9 4.2-0.3 10.3-2.3 6.4-0.4 6.6 1.1 31.6 10.6 4.9 3.2 1.4 5.4-0.4 4.6 0.4 4.3 3.8 4.6 3.3 1.5 30.6-0.4 5.8-1.9 11.8-9.3 32.1-16.2 12.9-10.1 10.2-14 1.4-4.1 2.3-11.3 4.9 4.6 2 4.1 1.9 7.6 4.2 5.2 4.7 4.1 3.2 4.1 1.3 5.2-0.7 2.9-1.7 2.9z",
                        "BEL3479" : "M228.2 105.4l0.3 0.8-8.6 5.4 4.5 10.8-6.3 4.4 7 2.3 6.1 10.8-9.3 17.8-11.4 7.4 29.3 21-5.6 4 7.8 13.4-6.8 10.1 4.4 6-8 3.7 12.5 6.2-5.2-0.1 3.9 4-1.8 1.8-6.1-0.9 2 4.7-3.5 3.5 2.2 5.6 6-3.7 4 1.7 2.1 7.7-6.1 6.3 16 13.9-3.4 4.2 6 8.2-0.6 3-16.2 12-11.6 8.7-11.7 12.3-1.1 3.1-4-0.9-6.4-4.7 0.4-5.6-5.6-7.9-11 1.6-10.6-6.1-11.6 6-3.2 1.1-4.3-7.7-4.3-4.2-6.8-2.1-20.6 6.5-2.4-15.1-4-2.6-15.1 6.1-0.2 1.8 5.3 6.3-9.8 4-1.1 3.8-7.4 1.5-3.7-3.4-1.7 0.5-6.9 4.2 5.3 16.9 1 3.9-20.7-9.5-4.8-4-1.3-2.6-3.5-8.2-1.5-2.7-9.6-9.3-1.7-3.1-0.8-2.7-1.3-2.4-3.4-1.8-2.5-0.3-4.8 0.7-5.9-0.9-1.9-0.8-1.4-2.4-2.2-5.2-1.2-1.3-3.1-1.6-0.9-1.4 0.3-1.9 2-4.6 0.3-2.6-5.1-18 1.5-3.8 5.9-6 1.6-2.5-0.9-8.1-4-6.2-9.3-10.3-2.6-8-2.6-18.1-3.9-9.5 5.2-3.9 9.9-3.2 35.1-26.7 53.9-31.9 52.1-34.4 58.2-18.9 2.2 16-1 10.2-0.2 9.7 3.6 10.4 6.3 6.9 1.9 0.9z",
                        "BEL3480" : "M705 100.3l-3.1 2.6-2.9 15.8 10.6 9.9 0.8 16.7-6.5 5.8-8.4-2.5-5.8 8.6-13 0.7-10.5 13.4-20.7 4.1 0 5.7-7.5 10.4-3.6-4.3-4.8 0.3-10 4.1-7 6.2-3.2-1.6-13.5 5.6-3.3-2.2-4.4-9.7-8.5 6.4-5.7-1.2-10.1 11.4 3-6.6-7-3.8-18.9 5.1-3.4 8.4-4-0.1-8.2-4.8-4.7 4.4-8.8-6.7-4.7 7.1-5.7-5.9-15.8 3.8-1.2-6.1-4.9-1.5 2.5-6.2-8.1 5.4-1.7-0.8 1.8-3-12.8-5.7-6.3-1.1-8.8 2.3-2.2-2.6-5.1 4.2-2.9-1.3-2.5-3 1.3-4.1-0.8-4.8-1.5-4.6-1.4-9.3 0.8-1.8 3.3-3.9 4.6-3.2 5.2-1 10.5 0 10.3-3.4 3.2-6.5 1.2-9-2-8.2-6.3-9 2.3-1.5-5.5-17.8 1.6-4.1 1-0.7 2.5-0.7 2.1-2.3 3.7-8-8.7-3.8-2-1.6-1.1-3.7 0.2-9-0.8-4.3-1.9-3.3-3.8-4.7 7.6 0.3 9.3 5.1 5 1.7 12.1 0.8 4.9-2.6 1.3-7.2-1.6-4.1-5.8-6.9-2-3.8 0.2-4.2 1.7-3.2 0.5-3-3.1-3.4 13.5-7.9 13.9-5.2 7.7-1.3 3.3 0.6 2 3.5-0.3 7-2.2 5.1-0.5 4.1 5 3.6 3.4 0.3 7.4-1.4 8.3 2.1 4-0.3 3.5-1.3 2.7-2 20-24.7 8.6-4.4 3.7 0.4 4.7 1.8 4.4 2.8 2.6 3.6 0.6 6.2-1.6 6-1.2 6.3 1.9 7.2-11.3-5.3-2.9 0.5-1.5 4.9 5.5 2.4 18.1 0.7 4.8 1.2 10 4.7 5.6-1.6 6.5-6.4 6-8.1 4.1-6.7 1.8-5.1 0.8-3.9 1.6-2.5 4-1 0.7 1 7.8 5.2 5.1 9.7-1 6.9-2.9 6.6-0.9 9 1.7 4.1 8.9 9.9 1.4 3.6 2.6 9.1 1.3 2.7 4.5 2.5 10-0.9 4.8 0.5 4.5 5.8 0.1 14.8 4.6 1.1 1.7-0.4z",
                        "BEL3481" : "M832.8 305.4l4.5 4.9 6.7-1.8 10.9 14.6 9.3-1.4 6.2 3.3 2.3-1 6-9.3-4.9-6.2 2.9 0.9 18.7 0.6 5.3-1-0.2 3 4.3 2.3 0.1 3.9-0.9 4.3 1 3.5 3.4 0.4 10.3-2.9 4.3 0.4 4.9 5 15.8 24.2-3.4 2.2 3 5.9 5.3 2.8 17.9 0.4 0.6 2.5-1.8 3.6-3 3.6 0 1.5-5.8 3.4-8.1 6.8-4.8 7.5 4.3 5.5-2 3.1 0.3 1.9 2.1 0.8 3 0.3-2 3.5 2.1 1.9 2.2 4.7 2.1 1.6 2.5 0.2 8.8-2.7 1.2-0.8 5.1 2.7 11.2 2.5 4.8 0.3-2.9 2.9-0.7 3.2 0.8 3.7 1.4 4.1 2.2 3.7 1.9 0.3 0.9 1.4-0.1 6.8-1.9 9.6-1.8 5.4 0.4 4.8 9 15.7 0.5 5.7-3.2 3.4-6.9 1.3-3.5-0.6-2.9-1.2-2.9-0.2-3.5 2-2.6 3.1-1.7 3.9-0.6 4.1 1.2 3.7-4.8 4.3-11.1 3.1-5.4 2.6-2.5 3.9-5.9 4.2-2.7 3.5 3.5 3.8 1.4 4.6-0.1 4.6-1.6 3.8-1.9 1.3-5.2 0.4-2.2 1.4-1.9 7.8 2.6 2.2 0.9 2.4-0.7 2.8-2.4 2.7-3.8-1.7-0.6-3.2 0.3-3.8-1.2-3.7-3.3-3.3-2 0.3-1.7 1.8-2.2 1.4-5.9 0.9-4.1-0.9 0.5-12.5-1.3-5.3 3.6-4.1-5.6-1.1-1.2-4.5 1.5-37.7-8.3-4.3-0.5-3.5-31.4-6.9-2.3 7.5 4.8 4.1-5.7 14.1 9 1.3-1.7 4-16.4 2.3-1.7 3.9-1.7-3.8-4.3-1.4-15.6 2.4 7-14.9-7-1.7-4.2-7.1-0.2-4.5 4.5-10.1-13.1 0 1.2-4.9-8.6 0.6-14-13.2-8.8 4.2-4.3-0.7-1.5-1.3 0.8-3.6-4.3-0.9-2.4-8-10.4 6.9-11.2-8.9-3.5 0.2-1.6 2.9 5.2 9.2-8.6 8.2-11-3.8-14.2 9.6-3.6-5 0.3-7.5-12.9-3.5-3.1-5.2 4-8-4.8-10.8-5.6-5.7-8.8 3.5-2.8-4.8 1.3-3.6-5.2 0.8-1.8-6.5 3.5-3.4-6.1 2.9-3.6-0.6-3.8-9.6-14.5-0.8 4.9-3.7-6.7-19.6-12.6-12 0.9-9.8 5-9.3-0.3-12.1 4.1-3-6.3-9.1 6.2-3.3 0.8-7.6 6.3 3.7-0.1 7.3 4.3 5.5 3.9 1.9 6.8-1.1 17.3 5.1 3.3-4.1-2.9-6.7 15.4 2 1.9-3.9 1.9 4.7 8.3-5.2 8.3 4.5 1.2-4.6 5.2-4.6 11.9-4.1 3.5 1.6 3.2 8 7 2.7 5.8-0.6 2-7.2 5.1 5.6 4.1-1 7-6.2-0.5-6.6 22.5-6.7 12.6-12.8 1.6 1 5.5 2.7 4.1 1.2-0.5 4.9-2.6 9.4 4.7 2.7 2.8 0.5 2.7-0.5 1.3-1.9z",
                        "BEL3482" : "M647.9 308.2l-0.8 7.6-6.2 3.3 6.3 9.1-4.1 3 0.3 12.1-5 9.3-8.2-1.9-5.9 8-6.9-0.6-0.3 2.5-27.7 7.4-1.4 1.2 1 4.8-2.9-4.3-10.4 4.1-1.6-8.3-6.7 1.9-1.2 4.5-7.2-5.1-2.5 0-2.9 4.9 3 12.7-5.8 1.3-9.4-3.3-4.6 0.6-4.4 3.5 0.9 6.9-15.6 6.6-3.8-1.7 0.3-3.8-8.5 4.5 0.7-10.1-1.2-2.1-11.7 0.1-2.7-2.8-14.7 1.6-6.5 7.2 0.7-10.4-7.6 0.8-6.5-10.1-8.4-1.8 1.4-8.5-13.8-3.5 1.3-8.6-5-6.6-2.9 1.2-8.1 11.2-6.1 0.9-4.5-1.9-2.5-7.2-0.3-15.1 1-5.3 1.4-1.5 2.1-4.1 6.3 0.1 1.8-4.5 2.6-1 2.5 1.1 5.6 7.8 7-0.2 8.5 7 5-3.7 7.2 2.4 3.8-1.2 1.8-2.2 0.7-6.2 5.6-0.1-2.7-4.1 1.9-2.2 4.9 6.8 4.9 0.4 1.5-6.2 12.5-1.9 9.4-7.4 5.7-1.8 3.7 0.6-1.1 4.7 1.3 2 8.2 4.8 0.6-5.1 11.3-2.6 6.1-6.7 1.4 8.9 8.7-0.5 5-5.8-4.8-6.4 1.1-10.5 13.5 3.5 4.6-2.6 1 3.1 2.3-5.2 6.9-2.5 10.8 3.7 5.6 9.3 6.2 0.6-1.1 4.4 19.5-2-0.9 7.4 3.3 2.7 3.9-0.6 12.7-10.1 9.8 7.8z"
                    }
                }
            }
        }
    );

    return Mapael;

}));