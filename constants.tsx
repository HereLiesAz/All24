import { Arcana, NolaLocation } from './types';

export const ARCANA_SHORT_DESCRIPTIONS: Record<Arcana, string> = {
  [Arcana.FOOL]: "Dive Bars",
  [Arcana.MAGICIAN]: "Queer Nightlife",
  [Arcana.EMPRESS]: "Modern Dining",
  [Arcana.EMPEROR]: "Grand Dames",
  [Arcana.HIEROPHANT]: "Museums",
  [Arcana.CHARIOT]: "Itineraries",
  [Arcana.HERMIT]: "Good Eats",
  [Arcana.WHEEL]: "Late Night",
  [Arcana.HANGED_MAN]: "Hidden Gems",
  [Arcana.DEATH]: "Gothic Terrain",
  [Arcana.DEVIL]: "Occult Shops",
  [Arcana.TOWER]: "Shitty Traps",
  [Arcana.MOON]: "Live Music",
  [Arcana.JUDGEMENT]: "NHEN's List",
};

export const NHEN_PICK_NAMES = [
  "Fetiche",
  "Boondock Saint",
  "Black Penny",
  "Jewel of the South",
  "Latitude 29",
  "Molly's at the Market",
  "Palm & Pine",
  "Fives",
  "Bar Tonique",
  "Dead on Decatur",
  "Vice & Graft",
  "Gimme",
  "Natty Adam's",
  "Keye's",
  "Cabaret",
  "Vice & Graft"
];

export const LOCATIONS: NolaLocation[] = [
  // --- THE FOOL (Dive Bars) [cite: 3, 4, 6] ---
  {
    id: "dive-1",
    name: "Checkpoint Charlie's",
    arcana: Arcana.FOOL,
    description: "\"Definitely saw a guy get stabbed outside...\"",
    address: "501 Esplanade Ave",
    vibe: "Stabbings, Laundry, Music",
    image: "https://images.unsplash.com/photo-1514933651103-005eec06c04b?auto=format&fit=crop&q=80&w=600"
  },
  {
    id: "dive-2",
    name: "High Grace NOLA",
    arcana: Arcana.FOOL,
    description: "\"Shitty bar. Shitty clientele...\"",
    address: "733 St Peter St",
    vibe: "Shitty, Dive",
    image: "https://images.unsplash.com/photo-1566417713940-fe7c737a9ef2?auto=format&fit=crop&q=80&w=600"
  },
  {
    id: "dive-3",
    name: "The Chart Room",
    arcana: Arcana.FOOL,
    description: "\"Yes, your feet will stick to the floor...\"",
    address: "300 Chartres St",
    vibe: "Sticky, Cash Only",
    image: "https://images.unsplash.com/photo-1585672840545-23c72b257540?auto=format&fit=crop&q=80&w=600"
  },
  {
    id: "dive-4",
    name: "Three Legged Dog",
    arcana: Arcana.FOOL,
    description: "\"Dirtiest dive bar... rats confirmed.\"",
    address: "400 Burgundy St",
    vibe: "Rats, Crawfish",
    image: "https://images.unsplash.com/photo-1534157483717-d266718d09e7?auto=format&fit=crop&q=80&w=600"
  },
  {
    id: "dive-5",
    name: "The Dungeon",
    arcana: Arcana.FOOL,
    description: "\"Hot Topic meets miniature golf.\"",
    address: "738 Toulouse St",
    vibe: "Metal, Cages",
    image: "https://images.unsplash.com/photo-1599951662973-1f196924b426?auto=format&fit=crop&q=80&w=600"
  },
  {
    id: "dive-6",
    name: "Aunt Tiki's",
    arcana: Arcana.FOOL,
    description: "\"Bartenders too hot to hydrate safely.\"",
    address: "1207 Decatur St",
    vibe: "24/7, Tiki, Grimy",
    image: "https://images.unsplash.com/photo-1511520638520-21b8eb648218?auto=format&fit=crop&q=80&w=600"
  },
  {
    id: "dive-7",
    name: "The Abbey",
    arcana: Arcana.FOOL,
    description: "\"Bathroom doors that don't lock.\"",
    address: "1123 Decatur St",
    vibe: "Goth, Seedy",
    image: "https://images.unsplash.com/photo-1575444758702-4a6b9222336e?auto=format&fit=crop&q=80&w=600"
  },
  {
    id: "dive-8",
    name: "Erin Rose",
    arcana: Arcana.FOOL,
    description: "\"Might catch a patron dancing on the bar.\"",
    address: "811 Conti St",
    vibe: "Frozen Irish Coffee",
    image: "https://images.unsplash.com/photo-1572116469696-31de0f17cc34?auto=format&fit=crop&q=80&w=600"
  },
  {
    id: "dive-9",
    name: "Cosimo's",
    arcana: Arcana.FOOL,
    description: "\"Wine wouldn't dress a salad.\"",
    address: "1201 Burgundy St",
    vibe: "Neighborhood, Dog Friendly",
    image: "https://images.unsplash.com/photo-1574096079513-d82599602950?auto=format&fit=crop&q=80&w=600"
  },
  {
    id: "dive-10",
    name: "Santos Bar",
    arcana: Arcana.FOOL,
    description: "\"Super dark... unfriendly bartender.\"",
    address: "1135 Decatur St",
    vibe: "Metal, Haunted",
    image: "https://images.unsplash.com/photo-1498038432885-c6f3f1b912ee?auto=format&fit=crop&q=80&w=600"
  },

  // --- THE DEVIL (Occult Shops) [cite: 7, 8, 33] ---
  {
    id: "shop-1",
    name: "Voodoo Authentica",
    arcana: Arcana.DEVIL,
    description: "Ink migrated to your kidney. Accept the damp sock.",
    address: "612 Dumaine St",
    vibe: "Handmade Gris-Gris",
    image: "https://images.unsplash.com/photo-1601053428987-a2f2601908d1?auto=format&fit=crop&q=80&w=600"
  },
  {
    id: "shop-2",
    name: "Hex: Old World Witchery",
    arcana: Arcana.DEVIL,
    description: "Gold in mechanical trout teeth.",
    address: "1219 Decatur St",
    vibe: "Warlock-owned",
    image: "https://images.unsplash.com/photo-1509356843151-3e7d96241e11?auto=format&fit=crop&q=80&w=600"
  },
  {
    id: "shop-3",
    name: "Reverend Zombie’s Voodoo Shop",
    arcana: Arcana.DEVIL,
    description: "Knowledge is yours if you can resist the urge to take a forbidden photo.",
    address: "723 St Peter St",
    vibe: "Masks, No Photos",
    image: "https://images.unsplash.com/photo-1596700589139-65d1d604471c?auto=format&fit=crop&q=80&w=600"
  },
  {
    id: "shop-4",
    name: "Dark Matter / Dead on Decatur",
    arcana: Arcana.DEVIL,
    description: "Beware cheese merchants. Purple triangle triangle success.",
    address: "Decatur St",
    vibe: "Oddities, Occult",
    image: "https://images.unsplash.com/photo-1535581652167-3d6b98c5b058?auto=format&fit=crop&q=80&w=600"
  },
  {
    id: "shop-5",
    name: "Crescent City Conjure",
    arcana: Arcana.DEVIL,
    description: "Hoodoo and custom items.",
    address: "2402 Royal St",
    vibe: "Hoodoo",
    image: "https://images.unsplash.com/photo-1596043430588-46654e81561e?auto=format&fit=crop&q=80&w=600"
  },
  {
    id: "shop-6",
    name: "Marie Laveau's House of Voodoo",
    arcana: Arcana.DEVIL,
    description: "Aura smells like 1984.",
    address: "739 Bourbon St",
    vibe: "Historical, Kitschy",
    image: "https://images.unsplash.com/photo-1506543730-1c390559635c?auto=format&fit=crop&q=80&w=600"
  },
  {
    id: "shop-7",
    name: "Esoterica Occult Goods",
    arcana: Arcana.DEVIL,
    description: "True enlightenment is yours, but only if you are 'serious'.",
    address: "541 Dumaine St",
    vibe: "Serious Practice",
    image: "https://images.unsplash.com/photo-1588665796985-c6579051888b?auto=format&fit=crop&q=80&w=600"
  },

  // --- THE EMPEROR (Grand Dames) [cite: 10, 11] ---
  {
    id: "dame-1",
    name: "Antoine’s",
    arcana: Arcana.EMPEROR,
    description: "Labyrinthine.",
    address: "713 St. Louis St",
    vibe: "History, Oysters Rockefeller",
    image: "https://images.unsplash.com/photo-1514362545857-3bc16c4c7d1b?auto=format&fit=crop&q=80&w=600"
  },
  {
    id: "dame-2",
    name: "Galatoire’s",
    arcana: Arcana.EMPEROR,
    description: "Bacchanalian.",
    address: "209 Bourbon St",
    vibe: "Friday Lunch, Tradition",
    image: "https://images.unsplash.com/photo-1559339352-11d035aa65de?auto=format&fit=crop&q=80&w=600"
  },
  {
    id: "dame-3",
    name: "Arnaud’s",
    arcana: Arcana.EMPEROR,
    description: "Theatrical.",
    address: "813 Bienville St",
    vibe: "Creole, French 75",
    image: "https://images.unsplash.com/photo-1550966871-3ed3c47e2ce2?auto=format&fit=crop&q=80&w=600"
  },
  {
    id: "dame-4",
    name: "Brennan’s",
    arcana: Arcana.EMPEROR,
    description: "Flaming.",
    address: "417 Royal St",
    vibe: "Pink, Bananas Foster",
    image: "https://images.unsplash.com/photo-1600093463592-8e36ae95ef56?auto=format&fit=crop&q=80&w=600"
  },

  // --- THE MAGICIAN (Queer Bars) [cite: 12, 13] ---
  {
    id: "queer-1",
    name: "Café Lafitte's in Exile",
    arcana: Arcana.MAGICIAN,
    description: "Founded 1933, safe harbor.",
    address: "901 Bourbon St",
    vibe: "Historic, 24/7",
    image: "https://images.unsplash.com/photo-1566417713940-fe7c737a9ef2?auto=format&fit=crop&q=80&w=600"
  },
  {
    id: "queer-2",
    name: "The Oz",
    arcana: Arcana.MAGICIAN,
    description: "HIV/AIDS-era hub.",
    address: "800 Bourbon St",
    vibe: "Dance, Drag",
    image: "https://images.unsplash.com/photo-1545128485-c400e7702796?auto=format&fit=crop&q=80&w=600"
  },
  {
    id: "queer-3",
    name: "Bourbon Pub & Parade",
    arcana: Arcana.MAGICIAN,
    description: "Saints & Sinners HQ.",
    address: "801 Bourbon St",
    vibe: "Multi-level, Party",
    image: "https://images.unsplash.com/photo-1516450360452-9312f5e86fc7?auto=format&fit=crop&q=80&w=600"
  },
  {
    id: "queer-4",
    name: "Lucille's Golden Lantern",
    arcana: Arcana.MAGICIAN,
    description: "Birthplace of Southern Decadence.",
    address: "1239 Royal St",
    vibe: "Local, Decadence",
    image: "https://images.unsplash.com/photo-1514933651103-005eec06c04b?auto=format&fit=crop&q=80&w=600"
  },
  {
    id: "queer-5",
    name: "The Crossing",
    arcana: Arcana.MAGICIAN,
    description: "Reclaimed grocery space.",
    address: "439 Dauphine St",
    vibe: "Friendly, Neighborhood",
    image: "https://images.unsplash.com/photo-1574096079513-d82599602950?auto=format&fit=crop&q=80&w=600"
  },
  {
    id: "queer-6",
    name: "Le Cabaret",
    arcana: Arcana.MAGICIAN,
    description: "cutest courtyard.",
    address: "New Orleans",
    vibe: "Courtyard, Cute",
    image: "https://images.unsplash.com/photo-1545128485-c400e7702796?auto=format&fit=crop&q=80&w=600"
  },

  // --- THE HIEROPHANT (Museums & Arts) [cite: 15, 16] ---
  {
    id: "cult-1",
    name: "New Orleans Historic Voodoo Museum",
    arcana: Arcana.HIEROPHANT,
    description: "45-60 min.",
    address: "724 Dumaine St",
    vibe: "Voodoo, History",
    image: "https://images.unsplash.com/photo-1601053428987-a2f2601908d1?auto=format&fit=crop&q=80&w=600"
  },
  {
    id: "cult-2",
    name: "Pharmacy Museum",
    arcana: Arcana.HIEROPHANT,
    description: "60-90 min.",
    address: "514 Chartres St",
    vibe: "Apothecary, Medical",
    image: "https://images.unsplash.com/photo-1532153955177-f59af40d6472?auto=format&fit=crop&q=80&w=600"
  },
  {
    id: "cult-3",
    name: "Storyville Museum",
    arcana: Arcana.HIEROPHANT,
    description: "1-2 hrs.",
    address: "1010 Conti St",
    vibe: "History, Red Light",
    image: "https://images.unsplash.com/photo-1550966871-3ed3c47e2ce2?auto=format&fit=crop&q=80&w=600"
  },
  {
    id: "cult-4",
    name: "M.S. Rau Gallery",
    arcana: Arcana.HIEROPHANT,
    description: "Fine & antique art.",
    address: "622 Royal St",
    vibe: "Fine Art, Antiques",
    image: "https://images.unsplash.com/photo-1518998053901-5348d3969104?auto=format&fit=crop&q=80&w=600"
  },
  {
    id: "cult-5",
    name: "Craig Tracy Gallery",
    arcana: Arcana.HIEROPHANT,
    description: "Body photography.",
    address: "827 Royal St",
    vibe: "Photography, Body Paint",
    image: "https://images.unsplash.com/photo-1452587925148-ce544e77e70d?auto=format&fit=crop&q=80&w=600"
  },
  {
    id: "cult-6",
    name: "Antieau Gallery",
    arcana: Arcana.HIEROPHANT,
    description: "Fiber & tapestry art.",
    address: "927 Royal St",
    vibe: "Fiber Art, Whimsical",
    image: "https://images.unsplash.com/photo-1555396273-367ea4eb4db5?auto=format&fit=crop&q=80&w=600"
  },

  // --- THE HERMIT / GOOD (Tourist Restaurants - Good) [cite: 17, 18, 19] ---
  {
    id: "good-1",
    name: "Napoleon House",
    arcana: Arcana.HERMIT,
    description: "A 200-year-old landmark.",
    address: "500 Chartres St",
    vibe: "Pimm's Cup, History",
    image: "https://images.unsplash.com/photo-1514362545857-3bc16c4c7d1b?auto=format&fit=crop&q=80&w=600"
  },
  {
    id: "good-2",
    name: "Muriel's Jackson Square",
    arcana: Arcana.HERMIT,
    description: "Fine food in a stunning setting.",
    address: "801 Chartres St",
    vibe: "Haunted, View",
    image: "https://images.unsplash.com/photo-1517248135467-4c7edcad34c4?auto=format&fit=crop&q=80&w=600"
  },
  {
    id: "good-3",
    name: "GW Fins",
    arcana: Arcana.HERMIT,
    description: "Premier seafood destination.",
    address: "808 Bienville St",
    vibe: "Seafood, Upscale",
    image: "https://images.unsplash.com/photo-1599488615731-7e5c547aae2e?auto=format&fit=crop&q=80&w=600"
  },
  {
    id: "good-4",
    name: "Red Fish Grill",
    arcana: Arcana.HERMIT,
    description: "Casual New Orleans seafood.",
    address: "115 Bourbon St",
    vibe: "Seafood, Casual",
    image: "https://images.unsplash.com/photo-1574096079513-d82599602950?auto=format&fit=crop&q=80&w=600"
  },

  // --- THE TOWER (Tourist Restaurants - Shitty Traps) [cite: 21, 22] ---
  {
    id: "worst-1",
    name: "Oceana Grill",
    arcana: Arcana.TOWER,
    description: "Flight-canceling poison.",
    address: "739 Conti St",
    vibe: "Poison, Danger",
    image: "https://images.unsplash.com/photo-1555939594-58d7cb561ad1?auto=format&fit=crop&q=80&w=600"
  },
  {
    id: "worst-2",
    name: "Acme Oyster House",
    arcana: Arcana.TOWER,
    description: "Objective culinary failure.",
    address: "724 Iberville St",
    vibe: "Failure, Lines",
    image: "https://images.unsplash.com/photo-1559339352-11d035aa65de?auto=format&fit=crop&q=80&w=600"
  },
  {
    id: "worst-3",
    name: "Willie's Chicken Shack",
    arcana: Arcana.TOWER,
    description: "Barbie-cell bathroom.",
    address: "Multiple Locations",
    vibe: "Chain, Jail",
    image: "https://images.unsplash.com/photo-1513639776629-7b611d22f46c?auto=format&fit=crop&q=80&w=600"
  },
  {
    id: "worst-4",
    name: "Mother's",
    arcana: Arcana.TOWER,
    description: "Gussied-up Red Lobster.",
    address: "401 Poydras St",
    vibe: "Overhyped, Touristy",
    image: "https://images.unsplash.com/photo-1529042410759-befb1204b468?auto=format&fit=crop&q=80&w=600"
  },
  {
    id: "worst-5",
    name: "Court of Two Sisters",
    arcana: Arcana.TOWER,
    description: "Atmospheric mediocrity.",
    address: "613 Royal St",
    vibe: "Buffet, Mediocre",
    image: "https://images.unsplash.com/photo-1514362545857-3bc16c4c7d1b?auto=format&fit=crop&q=80&w=600"
  },
  {
    id: "worst-6",
    name: "Pier 424",
    arcana: Arcana.TOWER,
    description: "Hell-fired redfish.",
    address: "424 Bourbon St",
    vibe: "Bad Seafood, Hell",
    image: "https://images.unsplash.com/photo-1574096079513-d82599602950?auto=format&fit=crop&q=80&w=600"
  },
  {
    id: "worst-7",
    name: "Ole Creole Cookery",
    arcana: Arcana.TOWER,
    description: "Hot garbage.",
    address: "500 Toulouse St",
    vibe: "Garbage, Touristy",
    image: "https://images.unsplash.com/photo-1555396273-367ea4eb4db5?auto=format&fit=crop&q=80&w=600"
  },
  {
    id: "worst-8",
    name: "Le Bayou",
    arcana: Arcana.TOWER,
    description: "Bourbon Street trash.",
    address: "208 Bourbon St",
    vibe: "Trash, Balcony",
    image: "https://images.unsplash.com/photo-1501612780327-45045538702b?auto=format&fit=crop&q=80&w=600"
  },
  {
    id: "worst-9",
    name: "Cafe Maspero",
    arcana: Arcana.TOWER,
    description: "Overpriced tourist feed.",
    address: "601 Decatur St",
    vibe: "Overpriced, Feed",
    image: "https://images.unsplash.com/photo-1550547660-d9450f859349?auto=format&fit=crop&q=80&w=600"
  },
  {
    id: "worst-10",
    name: "Corner Oyster House",
    arcana: Arcana.TOWER,
    description: "World's worst restaurant.",
    address: "500 St Peter St",
    vibe: "Worst, Avoid",
    image: "https://images.unsplash.com/photo-1559339352-11d035aa65de?auto=format&fit=crop&q=80&w=600"
  },

  // --- THE JUDGEMENT (NHEN's List) [cite: 24, 25, 27] ---
  {
    id: "nhen-1",
    name: "Fetiche",
    arcana: Arcana.JUDGEMENT,
    description: "Sophisticated boutique and lounge space.",
    address: "333 Chartres St",
    vibe: "Fetish, Lounge",
    image: "https://images.unsplash.com/photo-1596700589139-65d1d604471c?auto=format&fit=crop&q=80&w=600"
  },
  {
    id: "nhen-2",
    name: "Boondock Saint",
    arcana: Arcana.JUDGEMENT,
    description: "Local favorite.",
    address: "731 St Peter St",
    vibe: "Local, Drinks",
    image: "https://images.unsplash.com/photo-1514362545857-3bc16c4c7d1b?auto=format&fit=crop&q=80&w=600"
  },
  {
    id: "nhen-3",
    name: "Black Penny",
    arcana: Arcana.JUDGEMENT,
    description: "Rampart street staple.",
    address: "700 N Rampart St",
    vibe: "Cans, Locals",
    image: "https://images.unsplash.com/photo-1574096079513-d82599602950?auto=format&fit=crop&q=80&w=600"
  },
  {
    id: "nhen-4",
    name: "Jewel of the South",
    arcana: Arcana.JUDGEMENT,
    description: "Award winning cocktail bar.",
    address: "1026 St. Louis St",
    vibe: "Cocktails, Dining",
    image: "https://images.unsplash.com/photo-1514362545857-3bc16c4c7d1b?auto=format&fit=crop&q=80&w=600"
  },
  {
    id: "nhen-5",
    name: "Latitude 29",
    arcana: Arcana.JUDGEMENT,
    description: "Tiki perfection.",
    address: "321 N Peters St",
    vibe: "Tiki, Rum",
    image: "https://images.unsplash.com/photo-1511520638520-21b8eb648218?auto=format&fit=crop&q=80&w=600"
  },
  {
    id: "nhen-6",
    name: "Molly's at the Market",
    arcana: Arcana.JUDGEMENT,
    description: "Frozen Irish Coffee hub.",
    address: "1107 Decatur St",
    vibe: "Media, Frozen Drinks",
    image: "https://images.unsplash.com/photo-1514933651103-005eec06c04b?auto=format&fit=crop&q=80&w=600"
  },
  {
    id: "nhen-7",
    name: "Palm & Pine",
    arcana: Arcana.JUDGEMENT,
    description: "Late night excellence.",
    address: "308 N Rampart St",
    vibe: "Late Night, Food",
    image: "https://images.unsplash.com/photo-1504674900247-0877df9cc836?auto=format&fit=crop&q=80&w=600"
  },
  {
    id: "nhen-8",
    name: "Fives",
    arcana: Arcana.JUDGEMENT,
    description: "Jackson Square watching.",
    address: "801 Chartres St",
    vibe: "Oysters, Cocktails",
    image: "https://images.unsplash.com/photo-1574071318508-1cdbab80d002?auto=format&fit=crop&q=80&w=600"
  },
  {
    id: "nhen-9",
    name: "Bar Tonique",
    arcana: Arcana.JUDGEMENT,
    description: "Craft cocktails, no pretense.",
    address: "820 N Rampart St",
    vibe: "Cocktails, Chill",
    image: "https://images.unsplash.com/photo-1514362545857-3bc16c4c7d1b?auto=format&fit=crop&q=80&w=600"
  },
  {
    id: "nhen-10",
    name: "Dead on Decatur",
    arcana: Arcana.JUDGEMENT,
    description: "Oddities and drinks.",
    address: "Decatur St",
    vibe: "Goth, Oddities",
    image: "https://images.unsplash.com/photo-1535581652167-3d6b98c5b058?auto=format&fit=crop&q=80&w=600"
  },
  {
    id: "nhen-11",
    name: "Vice & Graft",
    arcana: Arcana.JUDGEMENT,
    description: "Nhen's Pick.",
    address: "New Orleans",
    vibe: "Shop, Curated",
    image: "https://images.unsplash.com/photo-1504674900247-0877df9cc836?auto=format&fit=crop&q=80&w=600"
  },
  {
    id: "nhen-12",
    name: "Gimme",
    arcana: Arcana.JUDGEMENT,
    description: "Nhen's Pick.",
    address: "New Orleans",
    vibe: "Shop, Boutique",
    image: "https://images.unsplash.com/photo-1511520638520-21b8eb648218?auto=format&fit=crop&q=80&w=600"
  },
  {
    id: "nhen-13",
    name: "Natty Adam's",
    arcana: Arcana.JUDGEMENT,
    description: "Nhen's Pick.",
    address: "New Orleans",
    vibe: "Tailor, Style",
    image: "https://images.unsplash.com/photo-1555396273-367ea4eb4db5?auto=format&fit=crop&q=80&w=600"
  },
  {
    id: "nhen-14",
    name: "Keye's",
    arcana: Arcana.JUDGEMENT,
    description: "Fuel Mart.",
    address: "1141 N Rampart St",
    vibe: "Fuel, 24/7",
    image: "https://images.unsplash.com/photo-1529042410759-befb1204b468?auto=format&fit=crop&q=80&w=600"
  },
  {
    id: "nhen-15",
    name: "Cabaret",
    arcana: Arcana.JUDGEMENT,
    description: "AllWays Lounge & Cabaret.",
    address: "2240 St Claude Ave",
    vibe: "Burlesque, Drag",
    image: "https://images.unsplash.com/photo-1545128485-c400e7702796?auto=format&fit=crop&q=80&w=600"
  },

  // --- THE WHEEL (Late Night Food) [cite: 28, 29] ---
  {
    id: "late-1",
    name: "Verti Marte",
    arcana: Arcana.WHEEL,
    description: "Essential 24-hour deli.",
    address: "1201 Royal St",
    vibe: "24/7, Po-boys",
    image: "https://images.unsplash.com/photo-1529042410759-befb1204b468?auto=format&fit=crop&q=80&w=600"
  },
  {
    id: "late-2",
    name: "Clover Grill",
    arcana: Arcana.WHEEL,
    description: "Burgers under a hubcap.",
    address: "900 Bourbon St",
    vibe: "Diner, 24/7",
    image: "https://images.unsplash.com/photo-1550547660-d9450f859349?auto=format&fit=crop&q=80&w=600"
  },
  {
    id: "late-3",
    name: "Melba's",
    arcana: Arcana.WHEEL,
    description: "Late night standard.",
    address: "1525 Elysian Fields Ave",
    vibe: "Po-boys, Laundry",
    image: "https://images.unsplash.com/photo-1513639776629-7b611d22f46c?auto=format&fit=crop&q=80&w=600"
  }
];
