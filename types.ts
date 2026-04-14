
export enum Arcana {
  FOOL = "The Fool",
  MAGICIAN = "The Magician",
  EMPRESS = "The Empress",
  EMPEROR = "The Emperor",
  HIEROPHANT = "The Hierophant",
  CHARIOT = "The Chariot",
  HERMIT = "The Hermit",
  WHEEL = "Wheel of Fortune",
  HANGED_MAN = "The Hanged Man",
  DEATH = "The Death",
  DEVIL = "The Devil",
  TOWER = "The Tower",
  MOON = "The Moon",
  JUDGEMENT = "Judgement",
}

export interface NolaLocation {
  id: string;
  name: string;
  arcana: Arcana;
  description: string;
  address: string;
  vibe: string;
  image: string;
}

export interface UserSettings {
  hotelAddress: string;
}
