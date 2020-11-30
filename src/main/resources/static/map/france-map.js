 var newData = {
	           'areas': {}
       };
        $(function () {
            $(".mapcontainer").mapael({
            	map: {
                    name: "france_departments",
                    zoom: {
                        enabled: false
                    },
                    defaultArea: {
                        attrs: {
                            fill: "white",
                            stroke: "black",
                            cursor: "pointer"
                        },
                        attrsHover: {
                            animDuration: 50,
                            fill: "white",
                        },
                        text: {
                            attrs: {
                                cursor: "pointer",
                                "font-size": 30,
                                fill: "#000"
                            },
                            attrsHover: {
                                animDuration: 0,
                            }
                        },
                        eventHandlers: {
                            click: function (e, id, mapElem, textElem) {
                                
                                console.log("Click: "+id+" color: "+document.getElementById("selectedColor").value);
                                if (mapElem.originalAttrs.fill == "white") {
                                    newData.areas[id] = {
                                        attrs: {
                                            fill: document.getElementById("selectedColor").value
                                        }
                                    };
                                } else {
                                    newData.areas[id] = {
                                        attrs: {
                                            fill: "white"
                                        }
                                    };
                                }
                                console.log(newData);
                                $(".mapcontainer").trigger('update', [{mapOptions: newData}]);
                            }
                        }
                    }
                },
                areas: {
                    "department-59": {
                        tooltip: {content: "<span style=\"font-weight:bold;\">Nord</span><br />"},
                        attrs: { fill : "red"}
                    },
                    "department-75": {
                        tooltip: {content: "<span style=\"font-weight:bold;\">Paris</span><br />"},
                        text: {content: "París"},
                        attrs: { fill : "blue"}
                    },
                    "department-13": {
                        tooltip: {content: "<span style=\"font-weight:bold;\">Bouches-du-Rhône</span><br />"},
                        attrs: { fill : "green"}
                    },
                    "department-69": {
                        tooltip: {content: "<span style=\"font-weight:bold;\">Rhône</span><br />"},
                        attrs: { fill : "yellow"}
                    },
                    "department-92": {
                        tooltip: {content: "<span style=\"font-weight:bold;\">Hauts-de-Seine </span><br />"},
                        attrs: { fill : "brown"}
                    },
                    "department-93": {
                        tooltip: {content: "<span style=\"font-weight:bold;\">Seine-Saint-Denis </span><br />"}
                    },
                    "department-62": {
                        tooltip: {content: "<span style=\"font-weight:bold;\">Pas-de-Calais </span><br />"}
                    },
                    "department-33": {
                        tooltip: {content: "<span style=\"font-weight:bold;\">Gironde </span><br />"}
                    },
                    "department-78": {
                        tooltip: {content: "<span style=\"font-weight:bold;\">Yvelines </span><br />"}
                    },
                    "department-77": {
                        tooltip: {content: "<span style=\"font-weight:bold;\">Seine-et-Marne</span><br />"}
                    },
                    "department-94": {
                        tooltip: {content: "<span style=\"font-weight:bold;\">Val-de-Marne </span><br />"}
                    },
                    "department-44": {
                        tooltip: {content: "<span style=\"font-weight:bold;\">Loire-Atlantique</span><br />"}
                    },
                    "department-76": {
                        tooltip: {content: "<span style=\"font-weight:bold;\">Seine-Maritime</span><br />"}
                    },
                    "department-31": {
                        tooltip: {content: "<span style=\"font-weight:bold;\">Haute-Garonne</span><br />"}
                    },
                    "department-38": {
                        tooltip: {content: "<span style=\"font-weight:bold;\">Isère</span><br />"}
                    },
                    "department-91": {
                        tooltip: {content: "<span style=\"font-weight:bold;\">Essonne </span><br />"}
                    },
                    "department-95": {
                        tooltip: {content: "<span style=\"font-weight:bold;\">Val-d'Oise </span><br />"}
                    },
                    "department-67": {
                        tooltip: {content: "<span style=\"font-weight:bold;\">Bas-Rhin</span><br />"}
                    },
                    "department-06": {
                        tooltip: {content: "<span style=\"font-weight:bold;\">Alpes-Maritimes </span><br />"}
                    },
                    "department-57": {
                        tooltip: {content: "<span style=\"font-weight:bold;\">Moselle </span><br />"}
                    },
                    "department-34": {
                        tooltip: {content: "<span style=\"font-weight:bold;\">Hérault </span><br />"}
                    },
                    "department-83": {
                        tooltip: {content: "<span style=\"font-weight:bold;\">Var </span><br />"}
                    },
                    "department-35": {
                        tooltip: {content: "<span style=\"font-weight:bold;\">Ille-et-Vilaine</span><br />"}
                    },
                    "department-29": {
                        tooltip: {content: "<span style=\"font-weight:bold;\">Finistère </span><br />"}
                    },
                    "department-974": {
                        tooltip: {content: "<span style=\"font-weight:bold;\">La Réunion </span><br />"}
                    },
                    "department-60": {
                        tooltip: {content: "<span style=\"font-weight:bold;\">Oise </span><br />"}
                    },
                    "department-49": {
                        tooltip: {content: "<span style=\"font-weight:bold;\">Maine-et-Loire </span><br />"}
                    },
                    "department-42": {
                        tooltip: {content: "<span style=\"font-weight:bold;\">Loire</span><br />"}
                    },
                    "department-68": {
                        tooltip: {content: "<span style=\"font-weight:bold;\">Haut-Rhin </span><br />"}
                    },
                    "department-74": {
                        tooltip: {content: "<span style=\"font-weight:bold;\">Haute-Savoie </span><br />"}
                    },
                    "department-54": {
                        tooltip: {content: "<span style=\"font-weight:bold;\">Meurthe-et-Moselle </span><br />"}
                    },
                    "department-56": {
                        tooltip: {content: "<span style=\"font-weight:bold;\">Morbihan </span><br />"}
                    },
                    "department-30": {
                        tooltip: {content: "<span style=\"font-weight:bold;\">Gard </span><br />"}
                    },
                    "department-14": {
                        tooltip: {content: "<span style=\"font-weight:bold;\">Calvados </span><br />"}
                    },
                    "department-45": {
                        tooltip: {content: "<span style=\"font-weight:bold;\">Loiret </span><br /"}
                    },
                    "department-64": {
                        tooltip: {content: "<span style=\"font-weight:bold;\">Pyrénées-Atlantiques</span><br />"}
                    },
                    "department-85": {
                        tooltip: {content: "<span style=\"font-weight:bold;\">Vendée </span><br />"}
                    },
                    "department-63": {
                        tooltip: {content: "<span style=\"font-weight:bold;\">Puy-de-Dôme</span><br />"}
                    },
                    "department-17": {
                        tooltip: {content: "<span style=\"font-weight:bold;\">Charente-Maritime</span><br />"}
                    },
                    "department-01": {
                        tooltip: {content: "<span style=\"font-weight:bold;\">Ain </span><br />"}
                    },
                    "department-22": {
                        tooltip: {content: "<span style=\"font-weight:bold;\">Côtes-d'Armor</span><br />"}
                    },
                    "department-37": {
                        tooltip: {content: "<span style=\"font-weight:bold;\">Indre-et-Loire</span><br />"}
                    },
                    "department-27": {
                        tooltip: {content: "<span style=\"font-weight:bold;\">Eure</span><br />"}
                    },
                    "department-80": {
                        tooltip: {content: "<span style=\"font-weight:bold;\">Somme </span><br />"}
                    },
                    "department-51": {
                        tooltip: {content: "<span style=\"font-weight:bold;\">Marne </span><br />"}
                    },
                    "department-72": {
                        tooltip: {content: "<span style=\"font-weight:bold;\">Sarthe </span><br />"}
                    },
                    "department-71": {
                        tooltip: {content: "<span style=\"font-weight:bold;\">Saône-et-Loire </span><br />"}
                    },
                    "department-84": {
                        tooltip: {content: "<span style=\"font-weight:bold;\">Vaucluse</span><br />"}
                    },
                    "department-02": {
                        tooltip: {content: "<span style=\"font-weight:bold;\">Aisne </span><br />"}
                    },
                    "department-25": {
                        tooltip: {content: "<span style=\"font-weight:bold;\">Doubs </span><br />"}
                    },
                    "department-21": {
                        tooltip: {content: "<span style=\"font-weight:bold;\">Côte-d'Or </span><br />"}
                    },
                    "department-50": {
                        tooltip: {content: "<span style=\"font-weight:bold;\">Manche </span><br /> "}
                    },
                    "department-26": {
                        tooltip: {content: "<span style=\"font-weight:bold;\">Drôme </span><br /> "}
                    },
                    "department-66": {
                        tooltip: {content: "<span style=\"font-weight:bold;\">Pyrénées-Orientales </span><br /> "}
                    },
                    "department-28": {
                        tooltip: {content: "<span style=\"font-weight:bold;\">Eure-et-Loir</span><br /> "}
                    },
                    "department-86": {
                        tooltip: {content: "<span style=\"font-weight:bold;\">Vienne</span><br />"}
                    },
                    "department-73": {
                        tooltip: {content: "<span style=\"font-weight:bold;\">Savoie</span><br />"}
                    },
                    "department-24": {
                        tooltip: {content: "<span style=\"font-weight:bold;\">Dordogne </span><br />"}
                    },
                    "department-971": {
                        tooltip: {content: "<span style=\"font-weight:bold;\">Guadeloupe </span><br />"}
                    },
                    "department-972": {
                        tooltip: {content: "<span style=\"font-weight:bold;\">Martinique </span><br />"}
                    },
                    "department-40": {
                        tooltip: {content: "<span style=\"font-weight:bold;\">Landes </span><br />"}
                    },
                    "department-88": {
                        tooltip: {content: "<span style=\"font-weight:bold;\">Vosges </span><br />"}
                    },
                    "department-81": {
                        tooltip: {content: "<span style=\"font-weight:bold;\">Tarn</span><br />"}
                    },
                    "department-87": {
                        tooltip: {content: "<span style=\"font-weight:bold;\">Haute-Vienne </span><br />"}
                    },
                    "department-79": {
                        tooltip: {content: "<span style=\"font-weight:bold;\">Deux-Sèvres </span><br />"}
                    },
                    "department-11": {
                        tooltip: {content: "<span style=\"font-weight:bold;\">Aude </span><br />"}
                    },
                    "department-16": {
                        tooltip: {content: "<span style=\"font-weight:bold;\">Charente</span><br />"}
                    },
                    "department-89": {
                        tooltip: {content: "<span style=\"font-weight:bold;\">Yonne</span><br />"}
                    },
                    "department-03": {
                        tooltip: {content: "<span style=\"font-weight:bold;\">Allier </span><br />"}
                    },
                    "department-47": {
                        tooltip: {content: "<span style=\"font-weight:bold;\">Lot-et-Garonne </span><br /> "}
                    },
                    "department-41": {
                        tooltip: {content: "<span style=\"font-weight:bold;\">Loir-et-Cher </span><br /> "}
                    },
                    "department-07": {
                        tooltip: {content: "<span style=\"font-weight:bold;\">Ardèche </span><br /> "}
                    },
                    "department-18": {
                        tooltip: {content: "<span style=\"font-weight:bold;\">Cher </span><br /> "}
                    },
                    "department-53": {
                        tooltip: {content: "<span style=\"font-weight:bold;\">Mayenne </span><br />"}
                    },
                    "department-10": {
                        tooltip: {content: "<span style=\"font-weight:bold;\">Aube </span><br /> "}
                    },
                    "department-61": {
                        tooltip: {content: "<span style=\"font-weight:bold;\">Orne </span><br / >"}
                    },
                    "department-08": {
                        tooltip: {content: "<span style=\"font-weight:bold;\">Ardennes </span><br />"}
                    },
                    "department-12": {
                        tooltip: {content: "<span style=\"font-weight:bold;\">Aveyron </span><br /> "}
                    },
                    "department-39": {
                        tooltip: {content: "<span style=\"font-weight:bold;\">Jura</span><br />"}
                    },
                    "department-19": {
                        tooltip: {content: "<span style=\"font-weight:bold;\">Corrèze </span><br /> "}
                    },
                    "department-82": {
                        tooltip: {content: "<span style=\"font-weight:bold;\">Tarn-et-Garonne </span><br /> "}
                    },
                    "department-70": {
                        tooltip: {content: "<span style=\"font-weight:bold;\">Haute-Saône </span><br />"}
                    },
                    "department-36": {
                        tooltip: {content: "<span style=\"font-weight:bold;\">Indre</span><br />"}
                    },
                    "department-65": {
                        tooltip: {content: "<span style=\"font-weight:bold;\">Hautes-Pyrénées</span><br />"}
                    },
                    "department-43": {
                        tooltip: {content: "<span style=\"font-weight:bold;\">Haute-Loire </span><br />"}
                    },
                    "department-973": {
                        tooltip: {content: "<span style=\"font-weight:bold;\">Guyane </span><br /> "}
                    },
                    "department-58": {
                        tooltip: {content: "<span style=\"font-weight:bold;\">Nièvre </span><br /> "}
                    },
                    "department-55": {
                        tooltip: {content: "<span style=\"font-weight:bold;\">Meuse </span><br /> "}
                    },
                    "department-32": {
                        tooltip: {content: "<span style=\"font-weight:bold;\">Gers </span><br /> "}
                    },
                    "department-52": {
                        tooltip: {content: "<span style=\"font-weight:bold;\">Haute-Marne</span><br />"}
                    },
                    "department-46": {
                        tooltip: {content: "<span style=\"font-weight:bold;\">Lot</span><br />"}
                    },
                    "department-2B": {
                        tooltip: {content: "<span style=\"font-weight:bold;\">Haute-Corse</span><br /> "}
                    },
                    "department-04": {
                        tooltip: {content: "<span style=\"font-weight:bold;\">Alpes-de-Haute-Provence</span><br /> "}
                    },
                    "department-09": {
                        tooltip: {content: "<span style=\"font-weight:bold;\">Ariège </span><br /> "}
                    },
                    "department-15": {
                        tooltip: {content: "<span style=\"font-weight:bold;\">Cantal </span><br />"}
                    },
                    "department-90": {
                        tooltip: {content: "<span style=\"font-weight:bold;\">Territoire de Belfort</span><br /> "}
                    },
                    "department-2A": {
                        tooltip: {content: "<span style=\"font-weight:bold;\">Corse-du-Sud </span><br />"}
                    },
                    "department-05": {
                        tooltip: {content: "<span style=\"font-weight:bold;\">Hautes-Alpes</span><br />"}
                    },
                    "department-23": {
                        tooltip: {content: "<span style=\"font-weight:bold;\">Creuse</span><br />"}
                    },
                    "department-48": {
                        tooltip: {content: "<span style=\"font-weight:bold;\">Lozère </span><br />"}
                    }
                }
            });
        });