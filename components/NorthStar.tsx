
import React from 'react';
import { IsolatedStarIcon } from './CustomIcons';

import { useState } from 'react';

import { UserSettings } from '../types';

export const NorthStar: React.FC = () => {
  const [toastMessage, setToastMessage] = useState<string | null>(null);

  const handleNavigate = () => {
    const settingsStr = localStorage.getItem('neon_occult_settings');
    if (settingsStr) {
      const settings: UserSettings = JSON.parse(settingsStr);
      if (settings.hotelAddress) {
        window.open(`https://www.google.com/maps/dir/?api=1&destination=${encodeURIComponent(settings.hotelAddress)}`, '_blank', 'noopener,noreferrer');
        return;
      }
    }
    setToastMessage("Consult the Altar (Settings) to bind your sanctuary's location.");
    setTimeout(() => setToastMessage(null), 3000);
  };

  return (
    <>
      {toastMessage && (
        <div className="fixed top-10 left-1/2 transform -translate-x-1/2 bg-[var(--accent-p)] text-white text-xs font-mono py-2 px-4 shadow-lg z-50 whitespace-nowrap border border-[var(--accent-c)] animate-in fade-in slide-in-from-top-4">
          {toastMessage}
        </div>
      )}
      <button
        onClick={handleNavigate}
        className="fixed bottom-24 right-6 w-16 h-16 bg-[#1a1a1a] border-2 border-[var(--accent-c)] rounded-none flex items-center justify-center text-[var(--accent-c)] z-40 hover:scale-105 transition-transform active:scale-95 group shadow-[0_0_25px_rgba(0,166,147,0.3)]"
        title="Return to Sanctuary"
      >
      <IsolatedStarIcon className="group-hover:animate-pulse pixel-icon" size={32} />
      <div className="absolute -top-12 right-0 bg-[var(--accent-c)] text-black text-[9px] px-2 py-1 font-bold rounded-none opacity-0 group-hover:opacity-100 transition-opacity font-digital whitespace-nowrap border border-black">
        NORTH STAR
      </div>
      </button>
    </>
  );
};
