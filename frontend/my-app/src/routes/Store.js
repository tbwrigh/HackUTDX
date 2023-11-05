import { writable } from "svelte/store";

const gender = writable(0);
const sexualOrientation = writable(0);
const race = writable(0);

export { gender, sexualOrientation, race };