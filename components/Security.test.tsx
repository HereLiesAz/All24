import { render, fireEvent } from '@testing-library/react';
import { NorthStar } from './NorthStar';
import { LocationCard } from './LocationCard';
import { vi, describe, it, expect, beforeEach } from 'vitest';
import React from 'react';
import { Arcana } from '../types';
import '@testing-library/jest-dom';

const mockLocation = {
  id: '1',
  name: 'Ghost Manor',
  address: '123 Eerie Ln',
  vibe: 'Spooky',
  description: 'A haunted mansion.',
  image: 'ghost.jpg',
  arcana: Arcana.MOON
};

describe('Security: Reverse Tabnabbing', () => {
  beforeEach(() => {
    vi.stubGlobal('window', { ...window, open: vi.fn() });
    localStorage.clear();
  });

  it('NorthStar opens Google Maps with noopener,noreferrer', () => {
    localStorage.setItem('neon_occult_settings', JSON.stringify({ hotelAddress: '123 Ghost St' }));
    const { getByTitle } = render(<NorthStar />);
    fireEvent.click(getByTitle('Return to Sanctuary'));

    expect(window.open).toHaveBeenCalledWith(
      expect.stringContaining('google.com/maps'),
      '_blank',
      'noopener,noreferrer'
    );
  });

  it('LocationCard opens Google Maps with noopener,noreferrer', () => {
    const { getByTitle } = render(<LocationCard location={mockLocation} revealed={true} />);
    fireEvent.click(getByTitle('Open Google Maps'));

    expect(window.open).toHaveBeenCalledWith(
      expect.stringContaining('google.com/maps'),
      '_blank',
      'noopener,noreferrer'
    );
  });
});
